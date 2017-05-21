using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WebServiceFoody;

namespace FoodyServer.Controllers
{
    public class AnGiController : ApiController
    {
        public class MonAnCustom
        {
            public int id;
            public String TenMonAn;
            public Binary HinhAnh;
            public String TenQuan;
            public String DiaChi;
            public String TenDuong;
            public String TenQuanHuyen;
            public String TenThanhPho;
        }
        [HttpGet]
        public List<MonAnCustom> GetAnGiAll()
        {
            DBFoodyDataContext db = new DBFoodyDataContext();
            var result = (
                 from monan in db.MonAns
                 join quanan in db.QuanAns on monan.MaQuanAn equals quanan.id
                 join duongquan in db.DuongPhos on quanan.MaDuong equals duongquan.MaDuong
                 join quanhuyen in db.QuanHuyens on duongquan.MaQuan equals quanhuyen.id
                 join thanhpho in db.ThanhPhos on quanhuyen.MaTP equals thanhpho.id
                 select new MonAnCustom
                 {
                     id = monan.MaMonAn,
                     TenMonAn = monan.TenMonAn,
                     HinhAnh = monan.HinhAnh,
                     TenQuan = quanan.TenQuan,
                     DiaChi = quanan.DiaChi,
                     TenDuong = duongquan.TenDuong,
                     TenQuanHuyen = quanhuyen.TenQuanHuyen,
                     TenThanhPho = thanhpho.TenThanhPho                    
                 });
            return result.ToList();
        }
        [HttpGet]
        public List<MonAnCustom> GetAnGiTheoDanhMuc(string TenDanhMuc)
        {
            if (TenDanhMuc == "Danh mục")
                return GetAnGiAll();
            DBFoodyDataContext db = new DBFoodyDataContext();
            var result = (
                 from monan in db.MonAns
                 join quanan in db.QuanAns on monan.MaQuanAn equals quanan.id
                 join duongquan in db.DuongPhos on quanan.MaDuong equals duongquan.MaDuong
                 join quanhuyen in db.QuanHuyens on duongquan.MaQuan equals quanhuyen.id
                 join thanhpho in db.ThanhPhos on quanhuyen.MaTP equals thanhpho.id
                 join danhmuc in db.DanhMucs on quanan.MaDanhMuc equals danhmuc.MaDanhMuc
                 where danhmuc.TenDanhMuc == TenDanhMuc
                 select new MonAnCustom
                 {
                     id = monan.MaMonAn,
                     TenMonAn = monan.TenMonAn,
                     HinhAnh = monan.HinhAnh,
                     TenQuan = quanan.TenQuan,
                     DiaChi = quanan.DiaChi,
                     TenDuong = duongquan.TenDuong,
                     TenQuanHuyen = quanhuyen.TenQuanHuyen,
                     TenThanhPho = thanhpho.TenThanhPho
                 });
            return result.ToList();
        }
        [HttpGet]
        public List<MonAnCustom> GetAnGiTheoThanhPho(string TenThanhPho)
        {
           
            DBFoodyDataContext db = new DBFoodyDataContext();
            var result = (
                 from monan in db.MonAns
                 join quanan in db.QuanAns on monan.MaQuanAn equals quanan.id
                 join duongquan in db.DuongPhos on quanan.MaDuong equals duongquan.MaDuong
                 join quanhuyen in db.QuanHuyens on duongquan.MaQuan equals quanhuyen.id
                 join thanhpho in db.ThanhPhos on quanhuyen.MaTP equals thanhpho.id
                 join danhmuc in db.DanhMucs on quanan.MaDanhMuc equals danhmuc.MaDanhMuc
                 where thanhpho.TenThanhPho == TenThanhPho
                 select new MonAnCustom
                 {
                     id = monan.MaMonAn,
                     TenMonAn = monan.TenMonAn,
                     HinhAnh = monan.HinhAnh,
                     TenQuan = quanan.TenQuan,
                     DiaChi = quanan.DiaChi,
                     TenDuong = duongquan.TenDuong,
                     TenQuanHuyen = quanhuyen.TenQuanHuyen,
                     TenThanhPho = thanhpho.TenThanhPho
                 });
            return result.ToList();
        }
        [HttpGet]
        public List<MonAnCustom> GetAnGiTheoQuanHuyen(string TenQuanHuyen)
        {

            DBFoodyDataContext db = new DBFoodyDataContext();
            var result = (
                 from monan in db.MonAns
                 join quanan in db.QuanAns on monan.MaQuanAn equals quanan.id
                 join duongquan in db.DuongPhos on quanan.MaDuong equals duongquan.MaDuong
                 join quanhuyen in db.QuanHuyens on duongquan.MaQuan equals quanhuyen.id
                 join thanhpho in db.ThanhPhos on quanhuyen.MaTP equals thanhpho.id
                 join danhmuc in db.DanhMucs on quanan.MaDanhMuc equals danhmuc.MaDanhMuc
                 where quanhuyen.TenQuanHuyen == TenQuanHuyen
                 select new MonAnCustom
                 {
                     id = monan.MaMonAn,
                     TenMonAn = monan.TenMonAn,
                     HinhAnh = monan.HinhAnh,
                     TenQuan = quanan.TenQuan,
                     DiaChi = quanan.DiaChi,
                     TenDuong = duongquan.TenDuong,
                     TenQuanHuyen = quanhuyen.TenQuanHuyen,
                     TenThanhPho = thanhpho.TenThanhPho
                 });
            return result.ToList();
        }
        [HttpGet]
        public List<MonAnCustom> GetAnGiTheoDuongPho(string TenDuong)
        {

            DBFoodyDataContext db = new DBFoodyDataContext();
            var result = (
                 from monan in db.MonAns
                 join quanan in db.QuanAns on monan.MaQuanAn equals quanan.id
                 join duongquan in db.DuongPhos on quanan.MaDuong equals duongquan.MaDuong
                 join quanhuyen in db.QuanHuyens on duongquan.MaQuan equals quanhuyen.id
                 join thanhpho in db.ThanhPhos on quanhuyen.MaTP equals thanhpho.id
                 join danhmuc in db.DanhMucs on quanan.MaDanhMuc equals danhmuc.MaDanhMuc
                 where duongquan.TenDuong == TenDuong
                 select new MonAnCustom
                 {
                     id = monan.MaMonAn,
                     TenMonAn = monan.TenMonAn,
                     HinhAnh = monan.HinhAnh,
                     TenQuan = quanan.TenQuan,
                     DiaChi = quanan.DiaChi,
                     TenDuong = duongquan.TenDuong,
                     TenQuanHuyen = quanhuyen.TenQuanHuyen,
                     TenThanhPho = thanhpho.TenThanhPho
                 });
            return result.ToList();
        }

        [HttpGet]
        public List<MonAnCustom> GetAnGiOptions(string TenDanhMuc, string KieuDiaDiem, string TenDiaDiem)
        {
            List<MonAnCustom> ListTheoDanhMuc = new List<MonAnCustom>();
            if (TenDanhMuc == "Danh mục")
                ListTheoDanhMuc = GetAnGiAll();
            else
                ListTheoDanhMuc = GetAnGiTheoDanhMuc(TenDanhMuc);

            //
            List<MonAnCustom> ListTheoDiaDiem = new List<MonAnCustom>();
            switch (KieuDiaDiem)
            {
                case "ThanhPho":
                    ListTheoDiaDiem = GetAnGiTheoThanhPho(TenDiaDiem);
                    break;
                case "QuanHuyen":
                    ListTheoDiaDiem = GetAnGiTheoQuanHuyen(TenDiaDiem);
                    break;
                case "DuongPho":
                    ListTheoDiaDiem = GetAnGiTheoDuongPho(TenDiaDiem);
                    break;
            }

            DBFoodyDataContext db = new DBFoodyDataContext();
            var result = (
                 from a in ListTheoDanhMuc
                 join b in ListTheoDiaDiem on a.id equals b.id
                 select new MonAnCustom
                 {
                     id = a.id,
                     TenMonAn = a.TenMonAn,
                     HinhAnh = a.HinhAnh,
                     TenQuan = a.TenQuan,
                     DiaChi = a.DiaChi,
                     TenDuong = a.TenDuong,
                     TenQuanHuyen = a.TenQuanHuyen,
                     TenThanhPho = a.TenThanhPho
                 });
            return result.ToList();

        }

    }
}
