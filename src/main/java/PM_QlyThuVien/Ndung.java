
package PM_QlyThuVien;


class Ndung {
    private int STT;
    private String MaBD, MaSV, Lop, Khoa, Hoten, Gioitinh, Ngaysinh, SDT, Gmail;
    private int Vipham;
    public Ndung(int STT, String MaBD, String MaSV, String Lop, String Khoa, String Hoten, String Gioitinh, String Ngaysinh, String SDT, String Gmail, int Vipham){
        this.STT = STT;
        this.MaBD = MaBD;
        this.MaSV = MaSV;
        this.Lop = Lop;
        this.Khoa = Khoa;
        this.Hoten = Hoten;
        this.Gioitinh = Gioitinh;
        this.Ngaysinh = Ngaysinh;
        this.SDT = SDT;
        this.Gmail = Gmail;
        this.Vipham = Vipham;
    }
    public int getSTT(){
        return STT;
    }
    public String getMaBD(){
        return MaBD;
    }
    public String getMaSV(){
        return MaSV;
    }
    public String getLop(){
        return Lop;
    }
    public String getKhoa(){
        return Khoa;
    }
    public String getHoten(){
        return Hoten;
    }
    public String getGioitinh(){
        return Gioitinh;
    }
    public String getNgaysinh(){
        return Ngaysinh;
    }
    public String getSDT(){
        return SDT;
    }
    public String getGmail(){
        return Gmail;
    }
    public int getVipham(){
        return Vipham;
    }       
}
