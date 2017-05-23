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
    public class ODauController : ApiController
    {
        public class QuanAnCustom
        {
            public int id;
            public string TenQuan;
            public string DiaChi;
            public Binary HinhAnh;
            public double? Diem;
            public string TenDuong;
            public string TenQuanHuyen;
            public string TenThanhPho;
        }
        [HttpGet]
        public List<QuanAnCustom> GetODauAll()
        {
            DBFoodyDataContext db = new DBFoodyDataContext();
            var result = (
                 from quanan in db.QuanAns
                 join duongpho in db.DuongPhos on quanan.MaDuong equals duongpho.MaDuong
                 join quanhuyen in db.QuanHuyens on duongpho.MaQuan equals quanhuyen.id
                 join thanhpho in db.ThanhPhos on quanhuyen.MaTP equals thanhpho.id
                 select new QuanAnCustom
                 {
                     id = quanan.id,
                     TenQuan = quanan.TenQuan,
                     DiaChi = quanan.DiaChi,
                     HinhAnh = quanan.HinhAnh,
                     Diem = quanan.Diem,
                     TenDuong = duongpho.TenDuong,
                     TenQuanHuyen = quanhuyen.TenQuanHuyen,
                     TenThanhPho = thanhpho.TenThanhPho
                 });
           
            return result.ToList();
        }
        [HttpGet]
        public List<QuanAnCustom> GetODauTheoDanhMuc(string TenDanhMuc)
        {
            if (TenDanhMuc == "Danh mục")
                return GetODauAll();
            DBFoodyDataContext db = new DBFoodyDataContext();
            var result = (
                 from quanan in db.QuanAns
                 join duongpho in db.DuongPhos on quanan.MaDuong equals duongpho.MaDuong
                 join quanhuyen in db.QuanHuyens on duongpho.MaQuan equals quanhuyen.id
                 join thanhpho in db.ThanhPhos on quanhuyen.MaTP equals thanhpho.id
                 join danhmuc in db.DanhMucs on quanan.MaDanhMuc equals danhmuc.MaDanhMuc
                 where danhmuc.TenDanhMuc == TenDanhMuc
                 select new QuanAnCustom
                 {
                     id = quanan.id,
                     TenQuan = quanan.TenQuan,
                     DiaChi = quanan.DiaChi,
                     HinhAnh = quanan.HinhAnh,
                     Diem = quanan.Diem,
                     TenDuong = duongpho.TenDuong,
                     TenQuanHuyen = quanhuyen.TenQuanHuyen,
                     TenThanhPho = thanhpho.TenThanhPho,
                 });
            return result.ToList();
        }
        [HttpGet]
        public List<QuanAnCustom> GetODauTheoThanhPho(string TenThanhPho)
        {
            DBFoodyDataContext db = new DBFoodyDataContext();
            var result = (
                 from quanan in db.QuanAns
                 join duongpho in db.DuongPhos on quanan.MaDuong equals duongpho.MaDuong
                 join quanhuyen in db.QuanHuyens on duongpho.MaQuan equals quanhuyen.id
                 join thanhpho in db.ThanhPhos on quanhuyen.MaTP equals thanhpho.id
                 join danhmuc in db.DanhMucs on quanan.MaDanhMuc equals danhmuc.MaDanhMuc
                 where thanhpho.TenThanhPho == TenThanhPho
                 select new QuanAnCustom
                 {
                     id = quanan.id,
                     TenQuan = quanan.TenQuan,
                     DiaChi = quanan.DiaChi,
                     HinhAnh = quanan.HinhAnh,
                     Diem = quanan.Diem,
                     TenDuong = duongpho.TenDuong,
                     TenQuanHuyen = quanhuyen.TenQuanHuyen,
                     TenThanhPho = thanhpho.TenThanhPho
                 });
            return result.ToList();
        }
        [HttpGet]
        public List<QuanAnCustom> GetODauTheoQuanHuyen(string TenQuanHuyen)
        {
            DBFoodyDataContext db = new DBFoodyDataContext();
            var result = (
                 from quanan in db.QuanAns
                 join duongpho in db.DuongPhos on quanan.MaDuong equals duongpho.MaDuong
                 join quanhuyen in db.QuanHuyens on duongpho.MaQuan equals quanhuyen.id
                 join thanhpho in db.ThanhPhos on quanhuyen.MaTP equals thanhpho.id
                 join danhmuc in db.DanhMucs on quanan.MaDanhMuc equals danhmuc.MaDanhMuc
                 where quanhuyen.TenQuanHuyen == TenQuanHuyen
                 select new QuanAnCustom
                 {
                     id = quanan.id,
                     TenQuan = quanan.TenQuan,
                     DiaChi = quanan.DiaChi,
                     HinhAnh = quanan.HinhAnh,
                     Diem = quanan.Diem,
                     TenDuong = duongpho.TenDuong,
                     TenQuanHuyen = quanhuyen.TenQuanHuyen,
                     TenThanhPho = thanhpho.TenThanhPho
                 });
            return result.ToList();
        }
        [HttpGet]
        public List<QuanAnCustom> GetODauTheoDuongPho(string TenDuong)
        {
            DBFoodyDataContext db = new DBFoodyDataContext();
            var result = (
                 from quanan in db.QuanAns
                 join duongpho in db.DuongPhos on quanan.MaDuong equals duongpho.MaDuong
                 join quanhuyen in db.QuanHuyens on duongpho.MaQuan equals quanhuyen.id
                 join thanhpho in db.ThanhPhos on quanhuyen.MaTP equals thanhpho.id
                 join danhmuc in db.DanhMucs on quanan.MaDanhMuc equals danhmuc.MaDanhMuc
                 where duongpho.TenDuong == TenDuong
                 select new QuanAnCustom
                 {
                     id = quanan.id,
                     TenQuan = quanan.TenQuan,
                     DiaChi = quanan.DiaChi,
                     HinhAnh = quanan.HinhAnh,
                     Diem = quanan.Diem,
                     TenDuong = duongpho.TenDuong,
                     TenQuanHuyen = quanhuyen.TenQuanHuyen,
                     TenThanhPho = thanhpho.TenThanhPho
                 });
            return result.ToList();
        }

        [HttpGet]
        public List<QuanAnCustom> GetODauOptions(string TenDanhMuc, string KieuDiaDiem, string TenDiaDiem)
        {
            List<QuanAnCustom> ListTheoDanhMuc = new List<QuanAnCustom>();
            if (TenDanhMuc == "Danh mục")
                ListTheoDanhMuc = GetODauAll();
            else
                ListTheoDanhMuc = GetODauTheoDanhMuc(TenDanhMuc);

            //
            List<QuanAnCustom> ListTheoDiaDiem = new List<QuanAnCustom>();
            switch (KieuDiaDiem)
            {
                case "ThanhPho":
                    ListTheoDiaDiem = GetODauTheoThanhPho(TenDiaDiem);
                    break;
                case "QuanHuyen":
                    ListTheoDiaDiem = GetODauTheoQuanHuyen(TenDiaDiem);
                    break;
                case "DuongPho":
                    ListTheoDiaDiem = GetODauTheoDuongPho(TenDiaDiem);
                    break;
            }

            DBFoodyDataContext db = new DBFoodyDataContext();
            var result = (
                 from a in ListTheoDanhMuc
                 join b in ListTheoDiaDiem on a.id equals b.id
                 orderby b.id descending
                 select new QuanAnCustom
                 {
                     id = a.id,
                     TenQuan = a.TenQuan,
                     DiaChi = a.DiaChi,
                     HinhAnh = a.HinhAnh,
                     Diem = a.Diem,
                     TenDuong = a.TenDuong,
                     TenQuanHuyen = a.TenQuanHuyen,
                     TenThanhPho = a.TenThanhPho
                 });
            return result.ToList();



        }

    }
}
