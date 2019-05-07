/*
 * Copyright (C) 2015 Bilibili
 * Copyright (C) 2015 Zhang Rui <bbcallen@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tv.danmaku.ijk.media.example.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tv.danmaku.ijk.media.example.R;

public class FileExplorerActivity extends FragmentActivity {

    private EditText mPathEt;
    private ListView mListView;
    private List<String> mPathList = new ArrayList<>();
    private List<File> mData = new ArrayList<>();
    private FileListAdapter mAdapter = new FileListAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        mPathList.add("/");

        mPathEt = findViewById(R.id.path_view);
        mListView = findViewById(R.id.file_list_view);

        mListView.setAdapter(mAdapter);

        notifyFileList();

        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File item = mAdapter.getItem(position);
                if (item.isDirectory()) {
                    mPathList.add(item.getName());
                    notifyFileList();
                } else {
                    VideoDemoActivity.intentTo(FileExplorerActivity.this,item.getAbsolutePath());
                }
            }
        });
    }

    private void notifyFileList() {
        String path = getAbsolutePath();
        File rootDir = new File(path);
        File[] files = rootDir.listFiles();
        if (files == null) {
            files = new File[0];
        }
        mData.clear();
        mData.addAll(Arrays.asList(files));
        mAdapter.notifyDataSetChanged();

        mPathEt.setText(path);
    }

    private String getAbsolutePath() {
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < mPathList.size(); i++) {
            String dir = mPathList.get(i);
            path.append(dir);
            if (i != 0) {
                path.append("/");
            }
        }
        return path.toString();
    }

    private class FileListAdapter extends BaseAdapter {

        final class ViewHolder {

            public ImageView iconImageView;
            public TextView nameTextView;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public File getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                view = inflater.inflate(R.layout.fragment_file_list_item, parent, false);
            }

            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (viewHolder == null) {
                viewHolder = new ViewHolder();
                viewHolder.iconImageView = (ImageView) view.findViewById(R.id.icon);
                viewHolder.nameTextView = (TextView) view.findViewById(R.id.name);
            }
            File fileItem = getItem(position);
            if (fileItem.isDirectory()) {
                viewHolder.iconImageView.setImageResource(R.drawable.ic_theme_folder);
            } else {
                viewHolder.iconImageView.setImageResource(R.drawable.ic_theme_play_arrow);
            }
            viewHolder.nameTextView.setText(fileItem.getName());
            return view;
        }
    }

    public void onClick(View view) {
        onBackPressedClick();
    }

    private void onBackPressedClick() {
        if (mPathList.size() > 1) {
            mPathList.remove(mPathList.size() - 1);
            notifyFileList();
        }
    }
}
