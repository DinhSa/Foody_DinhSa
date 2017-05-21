using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace WebServiceFoody.Controllers
{
    public class UserController : ApiController
    {
        [HttpGet]
        public Boolean Register(string Email, string pass, string name)
        {
            
            try
            {
                DBFoodyDataContext db = new DBFoodyDataContext();
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.TaiKhoan1 = Email;
                taikhoan.MatKhau = pass;
                taikhoan.TenHienThi = name;
                db.TaiKhoans.InsertOnSubmit(taikhoan);
                db.SubmitChanges();
                return true;

            }
            catch(Exception e)
            {
                return false;
            }


        }


        [HttpGet]
        public TaiKhoan Login(string email, string pass)
        {
            DBFoodyDataContext db = new DBFoodyDataContext();
            return db.TaiKhoans.FirstOrDefault(x => x.TaiKhoan1 == email & x.MatKhau == pass);
        
        }


        [HttpGet]
        public Boolean ChangePass(string Email, string NewPass, string OldPass)
        {
            try
            {
                DBFoodyDataContext db = new DBFoodyDataContext();
                TaiKhoan taikhoan = db.TaiKhoans.FirstOrDefault(x => x.TaiKhoan1 == Email & x.MatKhau==OldPass);
                if (taikhoan == null) return false;//không tồn tại false
                taikhoan.MatKhau = NewPass;
                db.SubmitChanges();//xác nhận chỉnh sửa
                return true;
            }
            catch
            {
                return false;
            }
        }

        public class CustomTK
        {
            public String TaiKhoan;
            public String MatKhau;
            public String TenHienThi;
            public String HinhDaiDien;
        }
        [HttpPost]
        public Boolean DoiHinhDaiDien(CustomTK taikhoan)
        {
            try
            {
                DBFoodyDataContext db = new DBFoodyDataContext();
                TaiKhoan tk = db.TaiKhoans.FirstOrDefault(x => x.TaiKhoan1 == taikhoan.TaiKhoan);
                if (tk == null) return false;

                tk.HinhDaiDien = Convert.FromBase64String(taikhoan.HinhDaiDien);
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
