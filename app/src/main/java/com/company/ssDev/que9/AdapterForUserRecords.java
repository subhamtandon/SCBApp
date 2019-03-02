package com.company.ssDev.que9;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class AdapterForUserRecords extends RecyclerView.Adapter<AdapterForUserRecords.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String > arrayList = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> ids = new ArrayList<>();
    String professional;
    String subject;

    public AdapterForUserRecords(RecyclerView recyclerView, Context context, String professional, String subject) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.professional = professional;
        this.subject = subject;
    }

    public void update(String recordName, String url, String id){
        arrayList.add(recordName);
        urls.add(url);
        ids.add(id);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_user_records, parent, false);
        return new AdapterForUserRecords.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        File file = context.getFileStreamPath(subject+"Record"+arrayList.get(position));
        if (file.exists()){
            holder.downloadPdf.setVisibility(View.GONE);
        }
        holder.textViewRecordName.setText(arrayList.get(position));
        holder.downloadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.seekBar.setVisibility(View.VISIBLE);
                holder.textView.setVisibility(View.VISIBLE);
                holder.seekBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                //holder.seekBar.getThumb().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        int val = (progress*(holder.seekBar.getWidth()-3*holder.seekBar.getThumbOffset()))/holder.seekBar.getMax();
                        holder.textView.setText(""+progress);
                        holder.textView.setX(holder.seekBar.getX()+val+holder.seekBar.getThumbOffset()/2);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                downloadPdf(arrayList.get(position), position, holder);
                //Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
        holder.textViewRecordName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    File file = context.getFileStreamPath(subject+"Record"+arrayList.get(position));
                    if (file.exists()){
                        openPdf(subject+"Record"+arrayList.get(position));
                    }else {
                        Intent intent = new Intent(context, PDFViewerActivity.class);
                        intent.putExtra("PDF_STRING", urls.get(position));
                        context.startActivity(intent);
                    }

                /*Uri webpage = Uri.parse(urls.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }*/
            }
        });
    }

    private void downloadPdf(final String s, final int pos, final ViewHolder hold) {
        new AsyncTask<Void, Integer, Boolean>(){

            @Override
            protected Boolean doInBackground(Void... params) {
                return downloadPdf();
            }
            @Nullable
            private Boolean downloadPdf(){
                try{
                    File file = context.getFileStreamPath(subject + "Record" + s);
                    if (file.exists())
                        return true;

                    try {
                        FileOutputStream fileOutputStream = context.openFileOutput(subject + "Record" + s, Context.MODE_PRIVATE);
                        URL u = new URL(urls.get(pos));
                        URLConnection connection = u.openConnection();
                        int contentLength =connection.getContentLength();
                        InputStream inputStream = new BufferedInputStream(u.openStream());
                        byte data[] = new byte[contentLength];
                        long total = 0;
                        int count;
                        while((count = inputStream.read(data)) != -1){
                            total += count;
                            publishProgress((int) ((total * 100) / contentLength));
                            fileOutputStream.write(data, 0, count);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        inputStream.close();
                        return true;
                    }catch (final Exception e){
                        e.printStackTrace();
                        return false;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                hold.seekBar.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean){
                    Toast.makeText(context, "Download done successfully", Toast.LENGTH_SHORT).show();
                    hold.seekBar.setVisibility(View.GONE);
                    hold.textView.setVisibility(View.GONE);
                    hold.downloadPdf.setVisibility(View.GONE);
                    openPdf(subject + "Record" + s);
                }else {
                    hold.seekBar.setVisibility(View.GONE);
                    hold.textView.setVisibility(View.GONE);
                    hold.downloadPdf.setVisibility(View.VISIBLE);
                    File dir = context.getFilesDir();
                    File file = new File(dir, subject + "Record" + s);
                    boolean deleted = file.delete();
                    Toast.makeText(context, "Unable to download this Pdf", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context, "ye lo ho gaya "+ deleted, Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private void openPdf(String s) {
        Intent intent = new Intent(context, OfflinePDFViewerActivity.class);
        intent.putExtra("filename", s);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewRecordName,textView;
        ImageView downloadPdf;
        SeekBar seekBar;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewRecordName = itemView.findViewById(R.id.textViewRecordName);
            downloadPdf = itemView.findViewById(R.id.downloadPdf);
            seekBar = itemView.findViewById(R.id.seekBarDownload);
            textView = itemView.findViewById(R.id.downloadText);
        }
    }
}
