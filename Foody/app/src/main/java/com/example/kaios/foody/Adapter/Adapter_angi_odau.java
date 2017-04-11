package com.example.kaios.foody.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaios.foody.QuanAn;
import com.example.kaios.foody.R;

import java.util.List;

/**
 * Created by kaios on 4/10/2017.
 */

public class Adapter_angi_odau extends RecyclerView.Adapter< RecyclerView.ViewHolder> {

    private static final int HEADER = 0;
    private static final int DANHMUC = 1;
    private static final int TYPE_DATA = 2;

    private Context context;
    private int imgHeader;
    private int[] imgdanhmuc;
    private String[] tvdanhmuc;
    private List<QuanAn> listQuanAn;

    public Adapter_angi_odau(Context context, int imgHeader, int[] imgDanhMuc, String[] tvDanhMuc,
                             List<QuanAn> listQuanAn) {
        this.context=context;
        this.imgHeader=imgHeader;
        this.imgdanhmuc=imgDanhMuc;
        this.tvdanhmuc=tvDanhMuc;
        this.listQuanAn=listQuanAn;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageview_header, parent, false);
            viewHolder = new HeaderItem(view);
        } else if (viewType == DANHMUC){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.girdview_danhmuc, parent, false);
            viewHolder = new DanhMucItem(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.body_odau, parent, false);
            viewHolder = new DataQuan(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderItem) {
            HeaderItem mHolder = (HeaderItem)holder;
            mHolder.img_quangcao.setImageResource(imgHeader);
        } else
        if (holder instanceof DanhMucItem){
            DanhMucItem mHolder = (DanhMucItem) holder;
            mHolder.img_DanhMuc.setImageResource(imgdanhmuc[position - 1]);
            mHolder.tv_DanhMuc.setText(tvdanhmuc[position - 1]);
        } else {
            DataQuan mHolder = (DataQuan) holder;
            QuanAn item = listQuanAn.get(position - tvdanhmuc.length - 1);
            mHolder.tvDiemQuan.setText(item.getTvDiem());
            mHolder.tvTenQuan.setText(item.getTvTen());
            mHolder.tvDiaDiem.setText(item.getTvDiaDiem() + ", " + item.getTvThanhPho());
            mHolder.imgODau.setImageBitmap(item.getImgHinh());
        }
    }

    @Override
    public int getItemCount() {
        return 1 + tvdanhmuc.length+ listQuanAn.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER;
        else if (position <= 10)
            return DANHMUC;
        else
            return TYPE_DATA;
    }



    public class HeaderItem extends RecyclerView.ViewHolder {

        private ImageView img_quangcao;
        public HeaderItem(View itemView) {
            super(itemView);

            img_quangcao = (ImageView) itemView.findViewById(R.id.imgHeader);
        }
    }

    public class DanhMucItem extends RecyclerView.ViewHolder {

        private ImageView img_DanhMuc;
        private TextView tv_DanhMuc;
        public DanhMucItem(View itemView) {
            super(itemView);
            img_DanhMuc = (ImageView) itemView.findViewById(R.id.imgDanhMuc);
            tv_DanhMuc = (TextView) itemView.findViewById(R.id.tvDanhMuc);
        }
    }

    public class DataQuan extends RecyclerView.ViewHolder {
        private TextView tvTenQuan;
        private TextView tvDiemQuan;
        private TextView tvDiaDiem;
        private ImageView imgODau;
        public DataQuan(View itemView) {
            super(itemView);
            tvDiaDiem = (TextView) itemView.findViewById(R.id.tvdiadiem);
            tvDiemQuan = (TextView) itemView.findViewById(R.id.tvdiem);
            tvTenQuan = (TextView) itemView.findViewById(R.id.tvtenquan);
            imgODau = (ImageView) itemView.findViewById(R.id.imgOdau);
        }
    }
}
