using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace WebServiceFoody.Controllers
{
    public class ThemDiaDiemController : ApiController
    {
        public class QuanAnCustom
        {
           
            public string TenDiaDiem;
            public string DiaChi;
            public string Img;
            public string SDT;
            public int MaDuong;
            public int MaDanhMuc;
            public double? Diem;
            public double? Lat;
            public double? Long;
            public string GioBatDau;
            public string GioKetThuc;
            public double? GiaThapNhat;
            public double? GiaCaoNhat;
            public string MoTa;
            public string ThoiGianThem;
        }


        [HttpPost]
        public bool ThemDiaDiemQuanAn(QuanAnCustom QA)
        {
            try
            {
                DBFoodyDataContext db = new DBFoodyDataContext();
                QuanAn quanan = new QuanAn();
               
                quanan.TenQuan =QA.TenDiaDiem;
                quanan.DiaChi = QA.DiaChi;
                if (QA.Img != null)
                {
                    quanan.HinhAnh = Convert.FromBase64String(QA.Img);
                }
                else
                {
                    quanan.HinhAnh = null;
                }
                
                quanan.SDT = QA.SDT;
                quanan.MaDuong = QA.MaDuong;
                quanan.MaDanhMuc = QA.MaDanhMuc;
                quanan.Diem = QA.Diem;
                quanan.Lat = Convert.ToSingle(QA.Lat);
                quanan.Long = Convert.ToSingle(QA.Long);
                quanan.GioBatDau = TimeSpan.Parse(QA.GioBatDau);
                quanan.GioKetThuc = TimeSpan.Parse(QA.GioKetThuc);
                quanan.GiaThapNhat = Convert.ToDecimal(QA.GiaThapNhat);
                quanan.GiaCaoNhat = Convert.ToDecimal(QA.GiaCaoNhat);
                quanan.MoTa = QA.MoTa;
                quanan.ThoiGianThem = DateTime.Parse(QA.ThoiGianThem);
                db.QuanAns.InsertOnSubmit(quanan);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
    }
}
