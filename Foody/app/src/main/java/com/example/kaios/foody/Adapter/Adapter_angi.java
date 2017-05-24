package com.example.kaios.foody.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.kaios.foody.MonAn;
import com.example.kaios.foody.R;

import java.util.List;

/**
 * Created by kaios on 4/11/2017.
 */

public class Adapter_angi extends RecyclerView.Adapter< RecyclerView.ViewHolder> {

    private static final int HEADER = 0;
    private static final int DANHMUC = 1;
    private static final int TYPE_DATA = 2;

    private Context context;
    private int imgHeader;
    private int[] imgdanhmuc;
    private String[] tvdanhmuc;
    private List<MonAn> listMonAn;

    public Adapter_angi(Context context, int imgHeader, int[] imgDanhMuc, String[] tvDanhMuc,
                             List<MonAn> listMonAn) {
        this.context=context;
        this.imgHeader=imgHeader;
        this.imgdanhmuc=imgDanhMuc;
        this.tvdanhmuc=tvDanhMuc;
        this.listMonAn=listMonAn;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == HEADER) {//hiển thị ảnh quảng cáo
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageview_header, parent, false);
            viewHolder = new Adapter_angi.HeaderItem(view);
        } else if (viewType == DANHMUC){//hiển thị danh mục
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.girdview_danhmuc, parent, false);
            viewHolder = new Adapter_angi.DanhMucItem(view);
        } else {//hiển thị quán ăn
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.body_angi, parent, false);
            viewHolder = new Adapter_angi.DataQuan(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof Adapter_angi.HeaderItem) {
            Adapter_angi.HeaderItem mHolder = (Adapter_angi.HeaderItem)holder;
            //mHolder.img_quangcao.setImageResource(imgHeader);
        } else
        if (holder instanceof Adapter_angi.DanhMucItem){
            Adapter_angi.DanhMucItem mHolder = (Adapter_angi.DanhMucItem) holder;
            mHolder.img_DanhMuc.setImageResource(imgdanhmuc[position - 1]);
            mHolder.tv_DanhMuc.setText(tvdanhmuc[position - 1]);
        } else {
            Adapter_angi.DataQuan mHolder = (Adapter_angi.DataQuan) holder;
            MonAn item = listMonAn.get(position - tvdanhmuc.length - 1);
            mHolder.imgAnGi.setImageBitmap(item.getImgHinh());
            mHolder.tvTenMon.setText(item.getTvTenMonAn());
            mHolder.tvTenQuan.setText(item.getTvTenQuanMonAn());
            mHolder.tvDiaChi.setText(item.getTvDiaDiemQuanMonAn()+ ",  " + item.getTvTenDuong()+ ",  " + item.getTvQuanHuyen() + ", " + item.getTvThanhPho());
        }
    }

    @Override
    public int getItemCount() {
        return 1 + tvdanhmuc.length+ listMonAn.size();
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

        private ViewFlipper viewFlipper;
        public HeaderItem(View itemView) {
            super(itemView);

            //slide hình
            viewFlipper = (ViewFlipper) itemView.findViewById(R.id.viewFlipper);

            viewFlipper.setFlipInterval(4000);
            viewFlipper.startFlipping();
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
        private TextView tvTenMon;
        private TextView tvDiaChi;
        private TextView tvTenQuan;
        private ImageView imgAnGi;
        public DataQuan(View itemView) {
            super(itemView);
            tvTenQuan = (TextView) itemView.findViewById(R.id.tvTenQuan);
            tvDiaChi = (TextView) itemView.findViewById(R.id.tvDiaChi);
            tvTenMon = (TextView) itemView.findViewById(R.id.tvTenMon);
            imgAnGi = (ImageView) itemView.findViewById(R.id.imgAngi);
        }
    }
}
