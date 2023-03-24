/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PM_QlyThuVien;

/**
 *
 * @author DIENMAYXANH
 */
class Sach {
    private int STT;
    private String Masach, Tensach, Tacgia, Theloai, Khoa;
    private int Soluong;
    public Sach(int STT, String Masach,String Tensach,String Tacgia,String Theloai,String Khoa, int Soluong)
    {
        this.STT = STT;
        this.Masach = Masach;
        this.Tensach = Tensach;
        this.Tacgia = Tacgia;
        this.Theloai = Theloai;
        this.Khoa = Khoa;
        this.Soluong = Soluong;
    }
    public int getSTT(){
        return STT;
    }
    public String getMasach(){
        return Masach;
    }
    public String getTensach(){
        return Tensach;
    }
    public String getTacgia(){
        return Tacgia;
    }
    public String getTheloai(){
        return Theloai;
    }
    public String getKhoa(){
        return Khoa;
    }
    public int getSoluong(){
        return Soluong;
    }
}