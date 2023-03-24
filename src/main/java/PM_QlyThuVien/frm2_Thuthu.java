/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PM_QlyThuVien;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author DIENMAYXANH
 */
public class frm2_Thuthu extends javax.swing.JFrame {

    /**
     * Creates new form
     */
    public frm2_Thuthu() {
        initComponents();
        show_Sach();
        show_Ndung();
        hienThiSLHomePage();
        hienThiSLNHomePage();
        hienThiSachdangmuon();
        lbNgayM.setVisible(false);
        txtNgayM_M.setVisible(false);
        lbNgayT.setVisible(false);
        txtNgayT_M.setVisible(false);
        J1.setVisible(false);
        J2.setVisible(false);
        txtNgayT.setVisible(false);
    }
    
    public KetNoi kn = new KetNoi();
    
    public void hienThiSachdangmuon(){
        try {
        Connection con = kn.getConnection();
        String query1 = "select SUM(Soluong) from MuonS";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query1);
        if(rs.next()){
        lbrSDM_H.setText(rs.getString(rs.getRow()));
        }
        } catch (Exception e) {
          //  e.printStackTrace();
        }
    }
    
    public void hienThiSLNHomePage(){
        try {
        Connection con = kn.getConnection();
        String query1 = "select count(STT) from TTBD";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query1);
        if(rs.next()){
        lbrSLBD_H.setText(rs.getString(rs.getRow()));
        }
        } catch (Exception e) {
          //  e.printStackTrace();
        }
    }
    
    public void hienThiSLHomePage(){
        try {
        Connection con = kn.getConnection();
        String query1 = "select SUM(Soluong) from Sach";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query1);
        if(rs.next()){
        lbrSLSach_H.setText(rs.getString(rs.getRow()));
        }
        } catch (Exception e) {
          //  e.printStackTrace();
        }
    }
    
    public ArrayList<Sach> sachList(){
        ArrayList<Sach> sachList = new ArrayList<>();
        try {
        Connection con = kn.getConnection();
        String query1 = "SELECT * FROM Sach";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query1);
        Sach sach;
        while(rs.next()){
            sach = new Sach(rs.getInt("STT"), rs.getString("Masach"), rs.getString("Tensach"),rs.getString("Tacgia"), rs.getString("Theloai"), rs.getString("Khoa"), rs.getInt("Soluong"));
            sachList.add(sach);
        }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
        return sachList;
    }
    
    public void show_Sach(){ //Hàm hiển thị bảng từ SQL ra màn hình
        tblSach.fixTable(jScrollPane1);
        ArrayList<Sach> list = sachList();
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getSTT();
            row[1] = list.get(i).getMasach();
            row[2] = list.get(i).getTensach();
            row[3] = list.get(i).getTacgia();
            row[4] = list.get(i).getTheloai();
            row[5] = list.get(i).getKhoa();
            row[6] = list.get(i).getSoluong();
            model.addRow(row);
        }
    }
    
    public void searchS(String str){
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblSach.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }
    
    public ArrayList<Ndung> ndList(){
        ArrayList<Ndung> ndList = new ArrayList<>();
        try {
        Connection con = kn.getConnection();
        String query1 = "SELECT * FROM TTBD";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query1);
        Ndung ndung;
        while(rs.next()){
            ndung = new Ndung(rs.getInt("STT"), rs.getString("MaBD"), rs.getString("MaSV"), rs.getString("Lop"), rs.getString("Khoa"), rs.getString("Hoten"), rs.getString("Gioitinh"), rs.getString("Ngaysinh"), rs.getString("SDT"), rs.getString("Gmail"), rs.getInt("Vipham"));
            ndList.add(ndung);
        }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
        return ndList;
        }
    
    public void show_Ndung(){
        tblNdung.fixTable(jScrollPane2);
        ArrayList<Ndung> list = ndList();
        DefaultTableModel model = (DefaultTableModel) tblNdung.getModel();
        Object[] row = new Object[11];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getSTT();
            row[1] = list.get(i).getMaBD();
            row[2] = list.get(i).getMaSV();
            row[3] = list.get(i).getLop();
            row[4] = list.get(i).getKhoa();
            row[5] = list.get(i).getHoten();
            row[6] = list.get(i).getGioitinh();
            row[7] = list.get(i).getNgaysinh();
            row[8] = list.get(i).getSDT();
            row[9] = list.get(i).getGmail();
            row[10] = list.get(i).getVipham();
            model.addRow(row);
           }
        }
        
    public void searchN(String str){
        DefaultTableModel model = (DefaultTableModel) tblNdung.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblNdung.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }
    
    public void hienthiTTS_M(){
        String MaS = txtMasach_M.getText();
        try {
        Connection con = kn.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * from Sach where Masach = ? ");
        ps.setString(1, MaS);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                lbrLoiMS.setText("");
                lbrMasach_M.setText(rs.getString("Masach"));
                lbrTensach_M.setText(rs.getString("Tensach"));
                lbrTacgia_M.setText(rs.getString("Tacgia"));
                lbrSoluong_M.setText(rs.getString("Soluong"));
            }else{
                lbrLoiMS.setText("Mã sách không khớp!");
                lbrMasach_M.setText("");
                lbrTensach_M.setText("");
                lbrTacgia_M.setText("");
                lbrSoluong_M.setText("");
            }
        } catch (Exception e) {
        }
    }
    
    public void hienthiTTBD_M(){
        String MaBD = txtMaBD_M.getText();
        try {
        Connection con = kn.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * from TTBD where MaBD = ? ");
        ps.setString(1, MaBD);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                lbrLoiBD.setText("");
                lbrMaBD_M.setText(rs.getString("MaBD"));
                lbrTenSV_M.setText(rs.getString("Hoten"));
                lbrLop_M.setText(rs.getString("Lop"));
                lbrSDT_M.setText(rs.getString("SDT"));
                lbrVP_M.setText(rs.getString("Vipham"));
            }else{
                lbrLoiBD.setText("Mã bạn đọc không khớp!");
                lbrMaBD_M.setText("");
                lbrTenSV_M.setText("");
                lbrLop_M.setText("");
                lbrSDT_M.setText("");
                lbrVP_M.setText("");
            }
        } catch (Exception e) {
        }
    }
    
    public void UpdateSL_M(){
        String Masach = txtMasach_M.getText();
        try {
        Connection con = kn.getConnection();
        int rSL = Integer.parseInt(lbrSoluong_M.getText());
        int SL = Integer.parseInt(txtSL_M.getText());
        PreparedStatement ps = con.prepareStatement("UPDATE Sach SET Soluong = Soluong - "+SL+" WHERE Masach = ? ");
        ps.setString(1, Masach);
        int Dem = ps.executeUpdate();
        if(Dem>0){
            JOptionPane.showMessageDialog(this, "Số lượng sách đã được cập nhật lại");
            lbrSoluong_M.setText(Integer.toString(rSL - SL));
        }else{
            JOptionPane.showMessageDialog(this, "Số lượng sách không được cập nhật lại");
        }
        } catch (Exception e) {
        }
    }
    
    public void hienthiThongtinTra(){
        String MaPM = txtMaPM_T.getText();
        try {
        Connection con = kn.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * from MuonS where MaPM = ? ");
        ps.setString(1, MaPM);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                lbrLoiMS1.setText("");
                lbrMasach.setText(rs.getString("Masach"));
                lbrMaBD.setText(rs.getString("MaBD"));
                lbrTenSV_T.setText(rs.getString("TenBD"));
                lbrLop_T.setText(rs.getString("Lop"));
                lbrTensach_T.setText(rs.getString("Tensach"));
                lbrSoluong_T.setText(rs.getString("Soluong"));
                lbrNgayM_T.setText(rs.getString("NgayM"));
                lbrNgayT_T.setText(rs.getString("NgayT"));
            }else{
                lbrLoiMS1.setText("Mã phieu mượn không khớp!");
                lbrTenSV_T.setText("");
                lbrLop_T.setText("");
                lbrTensach_T.setText("");
                lbrSoluong_T.setText("");
                lbrNgayM_T.setText("");
                lbrNgayT_T.setText("");
            }
        } catch (Exception e) {
        }
    }
    
    public void UpdateSL_T(){
        String Masach = lbrMasach.getText();
        try {
        Connection con = kn.getConnection();
        int SL = Integer.parseInt(lbrSoluong_T.getText());
        PreparedStatement ps = con.prepareStatement("UPDATE Sach SET Soluong = Soluong + "+SL+" WHERE Masach = ? ");
        ps.setString(1, Masach);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Đã cập nhật lại số lượng sách trong kho");
        } catch (Exception e) {
            
        }
    }
    
    public void Update_Vipham(){
        String MaBD = lbrMaBD.getText();
        try {
            Connection con = kn.getConnection();
            
            PreparedStatement ps = con.prepareStatement("UPDATE TTBD SET Vipham = Vipham+1 WHERE MaBD =?");
            if(chb4.isSelected()|chb6.isSelected()){
                ps.setString(1, MaBD);
            }
            
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void hienthiPhieuMuon(){
        String MaS = txtMaphieu_P.getText();
        try {
        Connection con = kn.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * from MuonS where MaPM = ? ");
        ps.setString(1, MaS);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                xLoiMP.setText("");
                lbrTen_P.setText(rs.getString("TenBD"));
                lbrLop_P.setText(rs.getString("Lop"));
                lbrMaS_P.setText(rs.getString("Masach"));
                lbrTenS_P.setText(rs.getString("Tensach"));
                lbrNgayM_P.setText(rs.getString("NgayM"));
                lbrNgayT_P.setText(rs.getString("NgayT"));
            }else{
                xLoiMP.setText("Mã phiếu không khớp!");
                lbrTen_P.setText("");
                lbrLop_P.setText("");
                lbrMaS_P.setText("");
                lbrTenS_P.setText("");
                lbrNgayM_P.setText("");
                lbrNgayT_P.setText("");
            }
        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        Menu = new javax.swing.JPanel();
        btnHomePage = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        x1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnQlySach = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        x2 = new javax.swing.JPanel();
        btnQlyND = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        x3 = new javax.swing.JPanel();
        btnQlyM = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        x4 = new javax.swing.JPanel();
        btnQlyT = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        x5 = new javax.swing.JPanel();
        btnPhieumuon = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        x6 = new javax.swing.JPanel();
        btnDangxuat = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        x7 = new javax.swing.JPanel();
        btnThoatPM = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        x8 = new javax.swing.JPanel();
        Menu_1 = new javax.swing.JTabbedPane();
        Home_Page = new javax.swing.JPanel();
        panelRound11 = new PM_QlyThuVien.PanelRound();
        jLabel17 = new javax.swing.JLabel();
        lbrSLSach_H = new javax.swing.JLabel();
        panelRound12 = new PM_QlyThuVien.PanelRound();
        jLabel18 = new javax.swing.JLabel();
        lbrSLBD_H = new javax.swing.JLabel();
        panelRound13 = new PM_QlyThuVien.PanelRound();
        jLabel19 = new javax.swing.JLabel();
        lbrSDM_H = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Qly_Sach = new javax.swing.JPanel();
        panelRound2 = new PM_QlyThuVien.PanelRound();
        txtMasach = new PM_QlyThuVien.TextField();
        txtTensach = new PM_QlyThuVien.TextField();
        txtTacgia = new PM_QlyThuVien.TextField();
        chbGiaotrinh = new checkbox.JCheckBoxCustom_1();
        chbSach = new checkbox.JCheckBoxCustom_1();
        chbLuanan = new checkbox.JCheckBoxCustom_1();
        txtSoluong = new PM_QlyThuVien.TextField();
        jLabel11 = new javax.swing.JLabel();
        cbKhoa = new combobox.Combobox();
        panelRound4 = new PM_QlyThuVien.PanelRound();
        btnThemS = new com.k33ptoo.components.KButton();
        btnSuaS = new com.k33ptoo.components.KButton();
        btnResetS = new com.k33ptoo.components.KButton();
        btnXoaS = new com.k33ptoo.components.KButton();
        btnExit_S = new com.k33ptoo.components.KButton();
        panelRound5 = new PM_QlyThuVien.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSach = new javaswingdev.swing.table.Table();
        txtSearchS = new javax.swing.JTextField();
        Qly_NguoiDoc = new javax.swing.JPanel();
        panelRound6 = new PM_QlyThuVien.PanelRound();
        txtMaBD_U = new PM_QlyThuVien.TextField();
        txtMaSV = new PM_QlyThuVien.TextField();
        txtLop = new PM_QlyThuVien.TextField();
        txtHoten = new PM_QlyThuVien.TextField();
        txtSDT = new PM_QlyThuVien.TextField();
        txtGmail = new PM_QlyThuVien.TextField();
        chbNam = new checkbox.JCheckBoxCustom_1();
        chbNu = new checkbox.JCheckBoxCustom_1();
        jLabel10 = new javax.swing.JLabel();
        cbKhoa1 = new combobox.Combobox();
        jLabel12 = new javax.swing.JLabel();
        cDate = new com.toedter.calendar.JDateChooser();
        panelRound7 = new PM_QlyThuVien.PanelRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNdung = new javaswingdev.swing.table.Table();
        panelRound8 = new PM_QlyThuVien.PanelRound();
        btnThemN = new com.k33ptoo.components.KButton();
        btnSuaN = new com.k33ptoo.components.KButton();
        btnResetN = new com.k33ptoo.components.KButton();
        btnXoaN = new com.k33ptoo.components.KButton();
        btnExitN = new com.k33ptoo.components.KButton();
        txtSearchND = new javax.swing.JTextField();
        Muon_Sach = new javax.swing.JPanel();
        Sach = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        lbrSoluong_M = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lbrTacgia_M = new javax.swing.JLabel();
        lbrTensach_M = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lbrMasach_M = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lbrLoiMS = new javax.swing.JLabel();
        Sinh_vien = new javax.swing.JPanel();
        lbrMaBD_M = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lbrTenSV_M = new javax.swing.JLabel();
        lbrLop_M = new javax.swing.JLabel();
        lbrSDT_M = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lbrLoiBD = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lbrVP_M = new javax.swing.JLabel();
        Phieu_muon = new com.k33ptoo.components.KGradientPanel();
        btnChomuon_M = new com.k33ptoo.components.KButton();
        jLabel42 = new javax.swing.JLabel();
        btnThoat_M = new com.k33ptoo.components.KButton();
        chb1 = new checkbox.JCheckBoxCustom();
        chb2 = new checkbox.JCheckBoxCustom();
        txtSL_M = new PM_QlyThuVien.TextField();
        txtNgayT_M = new com.toedter.calendar.JDateChooser();
        txtNgayM_M = new com.toedter.calendar.JDateChooser();
        lbNgayT = new javax.swing.JLabel();
        lbNgayM = new javax.swing.JLabel();
        txtMaPM_M = new PM_QlyThuVien.TextField();
        txtMasach_M = new PM_QlyThuVien.TextField();
        txtMaBD_M = new PM_QlyThuVien.TextField();
        Tra_Sach = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        lbrSoluong_T = new javax.swing.JLabel();
        lbrTensach_T = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        lbrTenSV_T = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        lbrLop_T = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        lbrNgayT_T = new javax.swing.JLabel();
        lbrNgayM_T = new javax.swing.JLabel();
        lbrLoiMS1 = new javax.swing.JLabel();
        kGradientPanel2 = new com.k33ptoo.components.KGradientPanel();
        J1 = new javax.swing.JLabel();
        txtNgayT = new com.toedter.calendar.JDateChooser();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        J2 = new javax.swing.JLabel();
        txtMaPM_T = new PM_QlyThuVien.TextField();
        jLabel67 = new javax.swing.JLabel();
        chb5 = new checkbox.JCheckBoxCustom();
        chb6 = new checkbox.JCheckBoxCustom();
        chb3 = new checkbox.JCheckBoxCustom();
        chb4 = new checkbox.JCheckBoxCustom();
        btnTrasach = new com.k33ptoo.components.KButton();
        btnThoat_T = new com.k33ptoo.components.KButton();
        lbrMaBD = new javax.swing.JLabel();
        lbrMasach = new javax.swing.JLabel();
        Phieu_Muon = new javax.swing.JPanel();
        Phieumuon = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        lbrTen_P = new javax.swing.JTextField();
        lbrLop_P = new javax.swing.JTextField();
        lbrMaS_P = new javax.swing.JTextField();
        lbrTenS_P = new javax.swing.JTextField();
        lbrNgayM_P = new javax.swing.JTextField();
        lbrNgayT_P = new javax.swing.JTextField();
        txtMaphieu_P = new PM_QlyThuVien.TextField();
        xLoiMP = new javax.swing.JLabel();
        btnIn = new com.k33ptoo.components.KButton();
        jPanel3 = new javax.swing.JPanel();
        btnThoat17 = new com.k33ptoo.components.KButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 630));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Menu.setBackground(new java.awt.Color(36, 45, 54));
        Menu.setPreferredSize(new java.awt.Dimension(260, 630));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHomePage.setBackground(new java.awt.Color(29, 38, 40));
        btnHomePage.setPreferredSize(new java.awt.Dimension(260, 55));
        btnHomePage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHomePageMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Home page");

        x1.setBackground(new java.awt.Color(255, 255, 255));
        x1.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x1Layout = new javax.swing.GroupLayout(x1);
        x1.setLayout(x1Layout);
        x1Layout.setHorizontalGroup(
            x1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x1Layout.setVerticalGroup(
            x1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnHomePageLayout = new javax.swing.GroupLayout(btnHomePage);
        btnHomePage.setLayout(btnHomePageLayout);
        btnHomePageLayout.setHorizontalGroup(
            btnHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHomePageLayout.createSequentialGroup()
                .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(0, 146, Short.MAX_VALUE))
        );
        btnHomePageLayout.setVerticalGroup(
            btnHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHomePageLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnHomePageLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnHomePage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("QUẢN LÝ THƯ VIỆN");
        Menu.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        btnQlySach.setBackground(new java.awt.Color(36, 45, 54));
        btnQlySach.setPreferredSize(new java.awt.Dimension(260, 55));
        btnQlySach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQlySachMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Quản lý sách");

        x2.setBackground(new java.awt.Color(255, 255, 255));
        x2.setOpaque(false);
        x2.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x2Layout = new javax.swing.GroupLayout(x2);
        x2.setLayout(x2Layout);
        x2Layout.setHorizontalGroup(
            x2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x2Layout.setVerticalGroup(
            x2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnQlySachLayout = new javax.swing.GroupLayout(btnQlySach);
        btnQlySach.setLayout(btnQlySachLayout);
        btnQlySachLayout.setHorizontalGroup(
            btnQlySachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlySachLayout.createSequentialGroup()
                .addComponent(x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(0, 138, Short.MAX_VALUE))
        );
        btnQlySachLayout.setVerticalGroup(
            btnQlySachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlySachLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnQlySachLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnQlySach, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        btnQlyND.setBackground(new java.awt.Color(36, 45, 54));
        btnQlyND.setPreferredSize(new java.awt.Dimension(260, 55));
        btnQlyND.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQlyNDMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Quản lý bạn đọc");

        x3.setBackground(new java.awt.Color(255, 255, 255));
        x3.setOpaque(false);
        x3.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x3Layout = new javax.swing.GroupLayout(x3);
        x3.setLayout(x3Layout);
        x3Layout.setHorizontalGroup(
            x3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x3Layout.setVerticalGroup(
            x3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnQlyNDLayout = new javax.swing.GroupLayout(btnQlyND);
        btnQlyND.setLayout(btnQlyNDLayout);
        btnQlyNDLayout.setHorizontalGroup(
            btnQlyNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlyNDLayout.createSequentialGroup()
                .addComponent(x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(0, 112, Short.MAX_VALUE))
        );
        btnQlyNDLayout.setVerticalGroup(
            btnQlyNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlyNDLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnQlyNDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnQlyND, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, -1, -1));

        btnQlyM.setBackground(new java.awt.Color(36, 45, 54));
        btnQlyM.setPreferredSize(new java.awt.Dimension(260, 55));
        btnQlyM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQlyMMousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mượn sách");

        x4.setBackground(new java.awt.Color(255, 255, 255));
        x4.setOpaque(false);
        x4.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x4Layout = new javax.swing.GroupLayout(x4);
        x4.setLayout(x4Layout);
        x4Layout.setHorizontalGroup(
            x4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x4Layout.setVerticalGroup(
            x4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnQlyMLayout = new javax.swing.GroupLayout(btnQlyM);
        btnQlyM.setLayout(btnQlyMLayout);
        btnQlyMLayout.setHorizontalGroup(
            btnQlyMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlyMLayout.createSequentialGroup()
                .addComponent(x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(0, 150, Short.MAX_VALUE))
        );
        btnQlyMLayout.setVerticalGroup(
            btnQlyMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlyMLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnQlyMLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnQlyM, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, -1, -1));

        btnQlyT.setBackground(new java.awt.Color(36, 45, 54));
        btnQlyT.setPreferredSize(new java.awt.Dimension(260, 55));
        btnQlyT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQlyTMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Trả sách");

        x5.setBackground(new java.awt.Color(255, 255, 255));
        x5.setOpaque(false);
        x5.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x5Layout = new javax.swing.GroupLayout(x5);
        x5.setLayout(x5Layout);
        x5Layout.setHorizontalGroup(
            x5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x5Layout.setVerticalGroup(
            x5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnQlyTLayout = new javax.swing.GroupLayout(btnQlyT);
        btnQlyT.setLayout(btnQlyTLayout);
        btnQlyTLayout.setHorizontalGroup(
            btnQlyTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlyTLayout.createSequentialGroup()
                .addComponent(x5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(0, 171, Short.MAX_VALUE))
        );
        btnQlyTLayout.setVerticalGroup(
            btnQlyTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlyTLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnQlyTLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnQlyT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, -1, -1));

        btnPhieumuon.setBackground(new java.awt.Color(36, 45, 54));
        btnPhieumuon.setPreferredSize(new java.awt.Dimension(260, 55));
        btnPhieumuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPhieumuonMousePressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Phiếu mượn");

        x6.setBackground(new java.awt.Color(255, 255, 255));
        x6.setOpaque(false);
        x6.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x6Layout = new javax.swing.GroupLayout(x6);
        x6.setLayout(x6Layout);
        x6Layout.setHorizontalGroup(
            x6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x6Layout.setVerticalGroup(
            x6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnPhieumuonLayout = new javax.swing.GroupLayout(btnPhieumuon);
        btnPhieumuon.setLayout(btnPhieumuonLayout);
        btnPhieumuonLayout.setHorizontalGroup(
            btnPhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPhieumuonLayout.createSequentialGroup()
                .addComponent(x6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addGap(0, 142, Short.MAX_VALUE))
        );
        btnPhieumuonLayout.setVerticalGroup(
            btnPhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPhieumuonLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPhieumuonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnPhieumuon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, -1, -1));

        btnDangxuat.setBackground(new java.awt.Color(36, 45, 54));
        btnDangxuat.setPreferredSize(new java.awt.Dimension(260, 55));
        btnDangxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDangxuatMousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Đăng xuất");

        x7.setBackground(new java.awt.Color(255, 255, 255));
        x7.setOpaque(false);
        x7.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x7Layout = new javax.swing.GroupLayout(x7);
        x7.setLayout(x7Layout);
        x7Layout.setHorizontalGroup(
            x7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x7Layout.setVerticalGroup(
            x7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnDangxuatLayout = new javax.swing.GroupLayout(btnDangxuat);
        btnDangxuat.setLayout(btnDangxuatLayout);
        btnDangxuatLayout.setHorizontalGroup(
            btnDangxuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDangxuatLayout.createSequentialGroup()
                .addComponent(x7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addGap(0, 156, Short.MAX_VALUE))
        );
        btnDangxuatLayout.setVerticalGroup(
            btnDangxuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDangxuatLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDangxuatLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnDangxuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, -1, -1));

        btnThoatPM.setBackground(new java.awt.Color(36, 45, 54));
        btnThoatPM.setPreferredSize(new java.awt.Dimension(260, 55));
        btnThoatPM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThoatPMMousePressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Thoát phần mềm");

        x8.setBackground(new java.awt.Color(255, 255, 255));
        x8.setOpaque(false);
        x8.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x8Layout = new javax.swing.GroupLayout(x8);
        x8.setLayout(x8Layout);
        x8Layout.setHorizontalGroup(
            x8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x8Layout.setVerticalGroup(
            x8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnThoatPMLayout = new javax.swing.GroupLayout(btnThoatPM);
        btnThoatPM.setLayout(btnThoatPMLayout);
        btnThoatPMLayout.setHorizontalGroup(
            btnThoatPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThoatPMLayout.createSequentialGroup()
                .addComponent(x8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addGap(0, 106, Short.MAX_VALUE))
        );
        btnThoatPMLayout.setVerticalGroup(
            btnThoatPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThoatPMLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnThoatPMLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnThoatPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, -1, -1));

        jPanel1.add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelRound11.setBackground(new java.awt.Color(255, 255, 255));
        panelRound11.setRoundBottomLeft(20);
        panelRound11.setRoundBottomRight(20);
        panelRound11.setRoundTopLeft(20);
        panelRound11.setRoundTopRight(20);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setText("TỔNG SỐ SÁCH");

        lbrSLSach_H.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbrSLSach_H.setText("   ");

        javax.swing.GroupLayout panelRound11Layout = new javax.swing.GroupLayout(panelRound11);
        panelRound11.setLayout(panelRound11Layout);
        panelRound11Layout.setHorizontalGroup(
            panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound11Layout.createSequentialGroup()
                .addGroup(panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound11Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel17))
                    .addGroup(panelRound11Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(lbrSLSach_H)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        panelRound11Layout.setVerticalGroup(
            panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound11Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel17)
                .addGap(43, 43, 43)
                .addComponent(lbrSLSach_H, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        panelRound12.setBackground(new java.awt.Color(255, 255, 255));
        panelRound12.setPreferredSize(new java.awt.Dimension(204, 196));
        panelRound12.setRoundBottomLeft(20);
        panelRound12.setRoundBottomRight(20);
        panelRound12.setRoundTopLeft(20);
        panelRound12.setRoundTopRight(20);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel18.setText("TỔNG SỐ BẠN ĐỌC");

        lbrSLBD_H.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbrSLBD_H.setText("   ");

        javax.swing.GroupLayout panelRound12Layout = new javax.swing.GroupLayout(panelRound12);
        panelRound12.setLayout(panelRound12Layout);
        panelRound12Layout.setHorizontalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound12Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel18)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbrSLBD_H)
                .addGap(90, 90, 90))
        );
        panelRound12Layout.setVerticalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound12Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel18)
                .addGap(42, 42, 42)
                .addComponent(lbrSLBD_H, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        panelRound13.setBackground(new java.awt.Color(255, 255, 255));
        panelRound13.setPreferredSize(new java.awt.Dimension(204, 196));
        panelRound13.setRoundBottomLeft(20);
        panelRound13.setRoundBottomRight(20);
        panelRound13.setRoundTopLeft(20);
        panelRound13.setRoundTopRight(20);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setText("SỐ SÁCH ĐANG MƯỢN");

        lbrSDM_H.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbrSDM_H.setText("   ");

        javax.swing.GroupLayout panelRound13Layout = new javax.swing.GroupLayout(panelRound13);
        panelRound13.setLayout(panelRound13Layout);
        panelRound13Layout.setHorizontalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound13Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel19)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbrSDM_H)
                .addGap(79, 79, 79))
        );
        panelRound13Layout.setVerticalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel19)
                .addGap(45, 45, 45)
                .addComponent(lbrSDM_H, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Add_BG/books-3733892_1920.jpg"))); // NOI18N

        javax.swing.GroupLayout Home_PageLayout = new javax.swing.GroupLayout(Home_Page);
        Home_Page.setLayout(Home_PageLayout);
        Home_PageLayout.setHorizontalGroup(
            Home_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Home_PageLayout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addComponent(panelRound11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(panelRound12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(panelRound13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
            .addGroup(Home_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Home_PageLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        Home_PageLayout.setVerticalGroup(
            Home_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Home_PageLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(Home_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(394, Short.MAX_VALUE))
            .addGroup(Home_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Home_PageLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Menu_1.addTab("tab8", Home_Page);

        Qly_Sach.setBackground(new java.awt.Color(215, 216, 219));

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(20);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        txtMasach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMasach.setLabelText("Mã sách");

        txtTensach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTensach.setLabelText("Tên sách");

        txtTacgia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTacgia.setLabelText("Tác giả");

        chbGiaotrinh.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(chbGiaotrinh);
        chbGiaotrinh.setText("Giáo trình");
        chbGiaotrinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        chbSach.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(chbSach);
        chbSach.setText("Sách");
        chbSach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        chbLuanan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(chbLuanan);
        chbLuanan.setText("Luận án");
        chbLuanan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSoluong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoluong.setLabelText("Số lượng");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Thể loại");

        cbKhoa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Công nghệ thông tin", "Công nghệ điện tử và truyền thông", "Công nghệ tự động hóa", "Hệ thống thông tin kinh tế", "Khoa học cơ bản", "Truyền thông đa phương tiện" }));
        cbKhoa.setSelectedIndex(-1);
        cbKhoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbKhoa.setLabeText("Khoa");
        cbKhoa.setLineColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addComponent(chbGiaotrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chbSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chbLuanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTacgia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTensach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMasach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMasach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTensach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbGiaotrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbLuanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setRoundBottomLeft(20);
        panelRound4.setRoundBottomRight(20);
        panelRound4.setRoundTopLeft(20);
        panelRound4.setRoundTopRight(20);

        btnThemS.setForeground(new java.awt.Color(0, 0, 0));
        btnThemS.setText("Thêm");
        btnThemS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemS.setkAllowGradient(false);
        btnThemS.setkBackGroundColor(new java.awt.Color(0, 0, 0));
        btnThemS.setkBorderRadius(20);
        btnThemS.setkEndColor(new java.awt.Color(0, 0, 0));
        btnThemS.setkFillButton(false);
        btnThemS.setkForeGround(new java.awt.Color(0, 0, 0));
        btnThemS.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnThemS.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnThemS.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnThemS.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnThemS.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnThemS.setkStartColor(new java.awt.Color(0, 0, 0));
        btnThemS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSActionPerformed(evt);
            }
        });

        btnSuaS.setForeground(new java.awt.Color(0, 0, 0));
        btnSuaS.setText("Sửa");
        btnSuaS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSuaS.setkAllowGradient(false);
        btnSuaS.setkBackGroundColor(new java.awt.Color(0, 0, 0));
        btnSuaS.setkBorderRadius(20);
        btnSuaS.setkEndColor(new java.awt.Color(0, 0, 0));
        btnSuaS.setkFillButton(false);
        btnSuaS.setkForeGround(new java.awt.Color(0, 0, 0));
        btnSuaS.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnSuaS.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnSuaS.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnSuaS.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnSuaS.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnSuaS.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSuaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSActionPerformed(evt);
            }
        });

        btnResetS.setForeground(new java.awt.Color(0, 0, 0));
        btnResetS.setText("Reset");
        btnResetS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetS.setkAllowGradient(false);
        btnResetS.setkBackGroundColor(new java.awt.Color(0, 0, 0));
        btnResetS.setkBorderRadius(20);
        btnResetS.setkEndColor(new java.awt.Color(0, 0, 0));
        btnResetS.setkFillButton(false);
        btnResetS.setkForeGround(new java.awt.Color(0, 0, 0));
        btnResetS.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnResetS.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnResetS.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnResetS.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnResetS.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnResetS.setkStartColor(new java.awt.Color(0, 0, 0));
        btnResetS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSActionPerformed(evt);
            }
        });

        btnXoaS.setForeground(new java.awt.Color(0, 0, 0));
        btnXoaS.setText("Xóa");
        btnXoaS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaS.setkAllowGradient(false);
        btnXoaS.setkBackGroundColor(new java.awt.Color(0, 0, 0));
        btnXoaS.setkBorderRadius(20);
        btnXoaS.setkEndColor(new java.awt.Color(0, 0, 0));
        btnXoaS.setkFillButton(false);
        btnXoaS.setkForeGround(new java.awt.Color(0, 0, 0));
        btnXoaS.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnXoaS.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnXoaS.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnXoaS.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnXoaS.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnXoaS.setkStartColor(new java.awt.Color(0, 0, 0));
        btnXoaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSActionPerformed(evt);
            }
        });

        btnExit_S.setForeground(new java.awt.Color(0, 0, 0));
        btnExit_S.setText("Back");
        btnExit_S.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExit_S.setkAllowGradient(false);
        btnExit_S.setkBackGroundColor(new java.awt.Color(0, 0, 0));
        btnExit_S.setkBorderRadius(20);
        btnExit_S.setkEndColor(new java.awt.Color(0, 0, 0));
        btnExit_S.setkFillButton(false);
        btnExit_S.setkForeGround(new java.awt.Color(0, 0, 0));
        btnExit_S.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnExit_S.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnExit_S.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnExit_S.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnExit_S.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnExit_S.setkStartColor(new java.awt.Color(0, 0, 0));
        btnExit_S.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit_SActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExit_S, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaS, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetS, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaS, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemS, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnThemS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnResetS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExit_S, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound5.setBackground(new java.awt.Color(255, 255, 255));
        panelRound5.setPreferredSize(new java.awt.Dimension(800, 300));
        panelRound5.setRoundBottomLeft(20);
        panelRound5.setRoundBottomRight(20);
        panelRound5.setRoundTopLeft(20);
        panelRound5.setRoundTopRight(20);

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sách", "Tên sách", "Tác giả", "Thể loại", "Khoa", "Số lượng"
            }
        ));
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSach);
        if (tblSach.getColumnModel().getColumnCount() > 0) {
            tblSach.getColumnModel().getColumn(0).setMinWidth(40);
            tblSach.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblSach.getColumnModel().getColumn(0).setMaxWidth(40);
            tblSach.getColumnModel().getColumn(1).setMinWidth(85);
            tblSach.getColumnModel().getColumn(1).setPreferredWidth(85);
            tblSach.getColumnModel().getColumn(1).setMaxWidth(85);
            tblSach.getColumnModel().getColumn(6).setMinWidth(85);
            tblSach.getColumnModel().getColumn(6).setPreferredWidth(85);
            tblSach.getColumnModel().getColumn(6).setMaxWidth(85);
        }

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtSearchS.setBackground(new java.awt.Color(215, 216, 219));
        txtSearchS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchS.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSearchS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout Qly_SachLayout = new javax.swing.GroupLayout(Qly_Sach);
        Qly_Sach.setLayout(Qly_SachLayout);
        Qly_SachLayout.setHorizontalGroup(
            Qly_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Qly_SachLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(Qly_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Qly_SachLayout.createSequentialGroup()
                        .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(Qly_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSearchS)
                            .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        Qly_SachLayout.setVerticalGroup(
            Qly_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Qly_SachLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(Qly_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(txtSearchS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        Menu_1.addTab("tab2", Qly_Sach);

        Qly_NguoiDoc.setBackground(new java.awt.Color(215, 216, 219));

        panelRound6.setBackground(new java.awt.Color(255, 255, 255));
        panelRound6.setRoundBottomLeft(20);
        panelRound6.setRoundBottomRight(20);
        panelRound6.setRoundTopLeft(20);
        panelRound6.setRoundTopRight(20);

        txtMaBD_U.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaBD_U.setLabelText("Mã bạn đọc");

        txtMaSV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSV.setLabelText("Mã SV");

        txtLop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLop.setLabelText("Lớp");

        txtHoten.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoten.setLabelText("Họ tên");

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDT.setLabelText("SĐT");

        txtGmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGmail.setLabelText("Email");

        chbNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(chbNam);
        chbNam.setText("Nam");
        chbNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        chbNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(chbNu);
        chbNu.setText("Nữ");
        chbNu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Giới tính");

        cbKhoa1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Công nghệ thông tin", "Công nghệ điện tử và truyền thông", "Công nghệ tự động hóa", "Hệ thống thông tin kinh tế", "Khoa học cơ bản", "Truyền thông đa phương tiện" }));
        cbKhoa1.setSelectedIndex(-1);
        cbKhoa1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbKhoa1.setLabeText("Khoa");
        cbKhoa1.setLineColor(new java.awt.Color(0, 0, 0));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Ngày sinh");

        cDate.setDateFormatString("yyyy-MM-dd");
        cDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbKhoa1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(txtLop, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaBD_U, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(86, 86, 86)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound6Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(18, 18, 18)
                            .addComponent(chbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chbNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHoten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaBD_U, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(chbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chbNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbKhoa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound7.setBackground(new java.awt.Color(255, 255, 255));
        panelRound7.setPreferredSize(new java.awt.Dimension(800, 300));
        panelRound7.setRoundBottomLeft(20);
        panelRound7.setRoundBottomRight(20);
        panelRound7.setRoundTopLeft(20);
        panelRound7.setRoundTopRight(20);

        tblNdung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sách", "Mã SV", "Lớp", "Khoa", "Họ tên", "Giới tính", "Ngày sinh", "SDT", "Email", "Vi phạm"
            }
        ));
        tblNdung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNdungMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNdung);
        if (tblNdung.getColumnModel().getColumnCount() > 0) {
            tblNdung.getColumnModel().getColumn(0).setMinWidth(40);
            tblNdung.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblNdung.getColumnModel().getColumn(0).setMaxWidth(40);
            tblNdung.getColumnModel().getColumn(1).setMinWidth(85);
            tblNdung.getColumnModel().getColumn(1).setPreferredWidth(85);
            tblNdung.getColumnModel().getColumn(1).setMaxWidth(85);
            tblNdung.getColumnModel().getColumn(6).setMinWidth(85);
            tblNdung.getColumnModel().getColumn(6).setPreferredWidth(85);
            tblNdung.getColumnModel().getColumn(6).setMaxWidth(85);
            tblNdung.getColumnModel().getColumn(10).setMinWidth(85);
            tblNdung.getColumnModel().getColumn(10).setPreferredWidth(85);
            tblNdung.getColumnModel().getColumn(10).setMaxWidth(85);
        }

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRound8.setBackground(new java.awt.Color(255, 255, 255));
        panelRound8.setRoundBottomLeft(20);
        panelRound8.setRoundBottomRight(20);
        panelRound8.setRoundTopLeft(20);
        panelRound8.setRoundTopRight(20);

        btnThemN.setForeground(new java.awt.Color(0, 0, 0));
        btnThemN.setText("Thêm");
        btnThemN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemN.setkAllowGradient(false);
        btnThemN.setkBackGroundColor(new java.awt.Color(0, 0, 0));
        btnThemN.setkBorderRadius(20);
        btnThemN.setkEndColor(new java.awt.Color(0, 0, 0));
        btnThemN.setkFillButton(false);
        btnThemN.setkForeGround(new java.awt.Color(0, 0, 0));
        btnThemN.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnThemN.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnThemN.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnThemN.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnThemN.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnThemN.setkStartColor(new java.awt.Color(0, 0, 0));
        btnThemN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNActionPerformed(evt);
            }
        });

        btnSuaN.setForeground(new java.awt.Color(0, 0, 0));
        btnSuaN.setText("Sửa");
        btnSuaN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSuaN.setkAllowGradient(false);
        btnSuaN.setkBackGroundColor(new java.awt.Color(0, 0, 0));
        btnSuaN.setkBorderRadius(20);
        btnSuaN.setkEndColor(new java.awt.Color(0, 0, 0));
        btnSuaN.setkFillButton(false);
        btnSuaN.setkForeGround(new java.awt.Color(0, 0, 0));
        btnSuaN.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnSuaN.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnSuaN.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnSuaN.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnSuaN.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnSuaN.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSuaN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNActionPerformed(evt);
            }
        });

        btnResetN.setForeground(new java.awt.Color(0, 0, 0));
        btnResetN.setText("Reset");
        btnResetN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetN.setkAllowGradient(false);
        btnResetN.setkBackGroundColor(new java.awt.Color(0, 0, 0));
        btnResetN.setkBorderRadius(20);
        btnResetN.setkEndColor(new java.awt.Color(0, 0, 0));
        btnResetN.setkFillButton(false);
        btnResetN.setkForeGround(new java.awt.Color(0, 0, 0));
        btnResetN.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnResetN.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnResetN.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnResetN.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnResetN.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnResetN.setkStartColor(new java.awt.Color(0, 0, 0));
        btnResetN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetNActionPerformed(evt);
            }
        });

        btnXoaN.setForeground(new java.awt.Color(0, 0, 0));
        btnXoaN.setText("Xóa");
        btnXoaN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaN.setkAllowGradient(false);
        btnXoaN.setkBackGroundColor(new java.awt.Color(0, 0, 0));
        btnXoaN.setkBorderRadius(20);
        btnXoaN.setkEndColor(new java.awt.Color(0, 0, 0));
        btnXoaN.setkFillButton(false);
        btnXoaN.setkForeGround(new java.awt.Color(0, 0, 0));
        btnXoaN.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnXoaN.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnXoaN.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnXoaN.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnXoaN.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnXoaN.setkStartColor(new java.awt.Color(0, 0, 0));
        btnXoaN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNActionPerformed(evt);
            }
        });

        btnExitN.setForeground(new java.awt.Color(0, 0, 0));
        btnExitN.setText("Back");
        btnExitN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExitN.setkAllowGradient(false);
        btnExitN.setkBackGroundColor(new java.awt.Color(0, 0, 0));
        btnExitN.setkBorderRadius(20);
        btnExitN.setkEndColor(new java.awt.Color(0, 0, 0));
        btnExitN.setkFillButton(false);
        btnExitN.setkForeGround(new java.awt.Color(0, 0, 0));
        btnExitN.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnExitN.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnExitN.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnExitN.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnExitN.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnExitN.setkStartColor(new java.awt.Color(0, 0, 0));
        btnExitN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound8Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExitN, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaN, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetN, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaN, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemN, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnThemN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnResetN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExitN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        txtSearchND.setBackground(new java.awt.Color(215, 216, 219));
        txtSearchND.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchND.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSearchND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchNDKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout Qly_NguoiDocLayout = new javax.swing.GroupLayout(Qly_NguoiDoc);
        Qly_NguoiDoc.setLayout(Qly_NguoiDocLayout);
        Qly_NguoiDocLayout.setHorizontalGroup(
            Qly_NguoiDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Qly_NguoiDocLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(Qly_NguoiDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Qly_NguoiDocLayout.createSequentialGroup()
                        .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Qly_NguoiDocLayout.createSequentialGroup()
                        .addGroup(Qly_NguoiDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Qly_NguoiDocLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtSearchND, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Qly_NguoiDocLayout.createSequentialGroup()
                                .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(79, 79, 79))))
        );
        Qly_NguoiDocLayout.setVerticalGroup(
            Qly_NguoiDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Qly_NguoiDocLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(Qly_NguoiDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        Menu_1.addTab("tab3", Qly_NguoiDoc);

        Muon_Sach.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Sach.setBackground(new java.awt.Color(242, 184, 208));
        Sach.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(255, 255, 255)));
        Sach.setPreferredSize(new java.awt.Dimension(330, 630));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Số lượng:");

        lbrSoluong_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Tác giả:");

        lbrTacgia_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbrTensach_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Tên sách:");

        lbrMasach_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Mã sách:");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Add_menu_icon/icons8-book-50.png"))); // NOI18N
        jLabel33.setText("SÁCH");

        lbrLoiMS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout SachLayout = new javax.swing.GroupLayout(Sach);
        Sach.setLayout(SachLayout);
        SachLayout.setHorizontalGroup(
            SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SachLayout.createSequentialGroup()
                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SachLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel33))
                    .addGroup(SachLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(SachLayout.createSequentialGroup()
                                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel30))
                                .addGap(18, 18, 18)
                                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbrTacgia_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbrMasach_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbrTensach_M, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(SachLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(18, 18, 18)
                                .addComponent(lbrSoluong_M, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(SachLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lbrLoiMS, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        SachLayout.setVerticalGroup(
            SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SachLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel33)
                .addGap(42, 42, 42)
                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32)
                    .addComponent(lbrMasach_M, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbrTensach_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(54, 54, 54)
                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbrTacgia_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(55, 55, 55)
                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbrSoluong_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                .addComponent(lbrLoiMS, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        Muon_Sach.add(Sach, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 630));

        Sinh_vien.setBackground(new java.awt.Color(241, 232, 217));
        Sinh_vien.setPreferredSize(new java.awt.Dimension(330, 630));

        lbrMaBD_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Mã bạn đọc");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Tên bạn đọc");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("Lớp:");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setText("SĐT:");

        lbrTenSV_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbrLop_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbrSDT_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Add_menu_icon/male_user_50px.png"))); // NOI18N
        jLabel38.setText("SINH VIÊN");

        lbrLoiBD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrLoiBD.setForeground(java.awt.Color.red);

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setText("Số lần VP:");

        lbrVP_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout Sinh_vienLayout = new javax.swing.GroupLayout(Sinh_vien);
        Sinh_vien.setLayout(Sinh_vienLayout);
        Sinh_vienLayout.setHorizontalGroup(
            Sinh_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sinh_vienLayout.createSequentialGroup()
                .addGroup(Sinh_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Sinh_vienLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel38))
                    .addGroup(Sinh_vienLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(Sinh_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Sinh_vienLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(32, 32, 32)
                                .addComponent(lbrVP_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(Sinh_vienLayout.createSequentialGroup()
                                .addGroup(Sinh_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(Sinh_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbrTenSV_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbrLop_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbrSDT_M, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                    .addComponent(lbrMaBD_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(lbrLoiBD, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        Sinh_vienLayout.setVerticalGroup(
            Sinh_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sinh_vienLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel38)
                .addGap(40, 40, 40)
                .addGroup(Sinh_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34)
                    .addComponent(lbrMaBD_M, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(Sinh_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(lbrTenSV_M, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(Sinh_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(lbrLop_M, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(Sinh_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbrSDT_M, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(62, 62, 62)
                .addGroup(Sinh_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(lbrVP_M, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(lbrLoiBD, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        Muon_Sach.add(Sinh_vien, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 320, 630));

        Phieu_muon.setBackground(new java.awt.Color(163, 200, 242));
        Phieu_muon.setkBorderRadius(0);
        Phieu_muon.setkEndColor(new java.awt.Color(163, 200, 242));
        Phieu_muon.setkFillBackground(false);
        Phieu_muon.setkStartColor(new java.awt.Color(163, 200, 242));

        btnChomuon_M.setForeground(new java.awt.Color(0, 0, 0));
        btnChomuon_M.setText("Cho mượn");
        btnChomuon_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChomuon_M.setkAllowGradient(false);
        btnChomuon_M.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        btnChomuon_M.setkBorderRadius(20);
        btnChomuon_M.setkEndColor(new java.awt.Color(0, 0, 0));
        btnChomuon_M.setkForeGround(new java.awt.Color(0, 0, 0));
        btnChomuon_M.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnChomuon_M.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnChomuon_M.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnChomuon_M.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnChomuon_M.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnChomuon_M.setkStartColor(new java.awt.Color(0, 0, 0));
        btnChomuon_M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChomuon_MActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel42.setText("MƯỢN SÁCH");

        btnThoat_M.setForeground(new java.awt.Color(0, 0, 0));
        btnThoat_M.setText("Thoát");
        btnThoat_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThoat_M.setkAllowGradient(false);
        btnThoat_M.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        btnThoat_M.setkBorderRadius(20);
        btnThoat_M.setkEndColor(new java.awt.Color(0, 0, 0));
        btnThoat_M.setkForeGround(new java.awt.Color(0, 0, 0));
        btnThoat_M.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnThoat_M.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnThoat_M.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnThoat_M.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnThoat_M.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnThoat_M.setkStartColor(new java.awt.Color(0, 0, 0));
        btnThoat_M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoat_MActionPerformed(evt);
            }
        });

        chb1.setBackground(new java.awt.Color(163, 200, 242));
        buttonGroup3.add(chb1);
        chb1.setText("Đọc tại chỗ");
        chb1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb1ActionPerformed(evt);
            }
        });

        chb2.setBackground(new java.awt.Color(163, 200, 242));
        buttonGroup3.add(chb2);
        chb2.setText("Mượn về");
        chb2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb2ActionPerformed(evt);
            }
        });

        txtSL_M.setBackground(new java.awt.Color(163, 200, 242));
        txtSL_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSL_M.setLabelText("Số lượng");

        txtNgayM_M.setBackground(new java.awt.Color(255, 255, 255));

        lbNgayT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbNgayT.setText("Ngày trả:");

        lbNgayM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbNgayM.setText("Ngày mượn:");

        txtMaPM_M.setBackground(new java.awt.Color(163, 200, 242));
        txtMaPM_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaPM_M.setLabelText("Mã phiếu mượn");

        txtMasach_M.setBackground(new java.awt.Color(163, 200, 242));
        txtMasach_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMasach_M.setLabelText("Mã sách");
        txtMasach_M.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMasach_MFocusLost(evt);
            }
        });

        txtMaBD_M.setBackground(new java.awt.Color(163, 200, 242));
        txtMaBD_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaBD_M.setLabelText("Mã bạn đọc");
        txtMaBD_M.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaBD_MFocusLost(evt);
            }
        });

        javax.swing.GroupLayout Phieu_muonLayout = new javax.swing.GroupLayout(Phieu_muon);
        Phieu_muon.setLayout(Phieu_muonLayout);
        Phieu_muonLayout.setHorizontalGroup(
            Phieu_muonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phieu_muonLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phieu_muonLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(Phieu_muonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phieu_muonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Phieu_muonLayout.createSequentialGroup()
                            .addComponent(btnThoat_M, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(btnChomuon_M, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtNgayT_M, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phieu_muonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbNgayT)
                        .addGroup(Phieu_muonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(Phieu_muonLayout.createSequentialGroup()
                                .addComponent(chb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSL_M, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaBD_M, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMasach_M, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaPM_M, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phieu_muonLayout.createSequentialGroup()
                                .addComponent(lbNgayM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNgayM_M, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(49, 49, 49))
        );
        Phieu_muonLayout.setVerticalGroup(
            Phieu_muonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phieu_muonLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel42)
                .addGap(30, 30, 30)
                .addComponent(txtMaPM_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(txtMasach_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtMaBD_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtSL_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(Phieu_muonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(Phieu_muonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNgayM)
                    .addComponent(txtNgayM_M, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(Phieu_muonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNgayT)
                    .addComponent(txtNgayT_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(Phieu_muonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThoat_M, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChomuon_M, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        Muon_Sach.add(Phieu_muon, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 340, 630));

        Menu_1.addTab("tab4", Muon_Sach);

        Tra_Sach.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(242, 184, 208));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(255, 255, 255)));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel55.setText("Số lượng:");

        lbrSoluong_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrSoluong_T.setBorder(new javax.swing.border.MatteBorder(null));

        lbrTensach_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrTensach_T.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel56.setText("Tên sách:");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Add_menu_icon/icons8-book-50.png"))); // NOI18N
        jLabel57.setText("SÁCH");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel58.setText("Tên SV:");

        lbrTenSV_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrTenSV_T.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel59.setText("Lớp:");

        lbrLop_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrLop_T.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel60.setText("Ngày mượn:");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel61.setText("Ngày trả:");

        lbrNgayT_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrNgayT_T.setBorder(new javax.swing.border.MatteBorder(null));

        lbrNgayM_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrNgayM_T.setBorder(new javax.swing.border.MatteBorder(null));

        lbrLoiMS1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrLoiMS1.setText("jLabel16");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel57))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addGap(23, 23, 23)
                                .addComponent(lbrTensach_T, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addGap(22, 22, 22)
                                .addComponent(lbrSoluong_T, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel58)
                                    .addComponent(jLabel59))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbrTenSV_T, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                    .addComponent(lbrLop_T, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(lbrLoiMS1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel60)
                                    .addComponent(jLabel61))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbrNgayT_T, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                    .addComponent(lbrNgayM_T, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel57)
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(lbrTenSV_T, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59)
                    .addComponent(lbrLop_T, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbrTensach_T, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel56))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbrSoluong_T, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbrNgayM_T, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrNgayT_T, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lbrLoiMS1)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        Tra_Sach.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 630));

        kGradientPanel2.setBackground(new java.awt.Color(163, 200, 242));
        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(163, 200, 242));
        kGradientPanel2.setkFillBackground(false);
        kGradientPanel2.setkStartColor(new java.awt.Color(163, 200, 242));

        J1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        J1.setText("Ngày trả");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel64.setText("TRẢ SÁCH");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel65.setText("Tình trạng sách:");

        J2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        J2.setText("thực tế:");

        txtMaPM_T.setBackground(new java.awt.Color(163, 200, 242));
        txtMaPM_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaPM_T.setLabelText("Mã phiếu mượn");
        txtMaPM_T.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaPM_TFocusLost(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel67.setText("Thời gian trả:");

        chb5.setBackground(new java.awt.Color(163, 200, 242));
        buttonGroup5.add(chb5);
        chb5.setText("Trả trước/đúng hạn");
        chb5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb5ActionPerformed(evt);
            }
        });

        chb6.setBackground(new java.awt.Color(163, 200, 242));
        buttonGroup5.add(chb6);
        chb6.setText("Trả muộn");
        chb6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb6ActionPerformed(evt);
            }
        });

        chb3.setBackground(new java.awt.Color(163, 200, 242));
        buttonGroup4.add(chb3);
        chb3.setText("Nguyên vẹn");
        chb3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        chb4.setBackground(new java.awt.Color(163, 200, 242));
        buttonGroup4.add(chb4);
        chb4.setText("Bị rách, hư hỏng");
        chb4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnTrasach.setForeground(new java.awt.Color(0, 0, 0));
        btnTrasach.setText("Trả sách");
        btnTrasach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTrasach.setkAllowGradient(false);
        btnTrasach.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        btnTrasach.setkBorderRadius(20);
        btnTrasach.setkEndColor(new java.awt.Color(0, 0, 0));
        btnTrasach.setkForeGround(new java.awt.Color(0, 0, 0));
        btnTrasach.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnTrasach.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnTrasach.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnTrasach.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnTrasach.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnTrasach.setkStartColor(new java.awt.Color(0, 0, 0));
        btnTrasach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrasachActionPerformed(evt);
            }
        });

        btnThoat_T.setForeground(new java.awt.Color(0, 0, 0));
        btnThoat_T.setText("Thoát");
        btnThoat_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThoat_T.setkAllowGradient(false);
        btnThoat_T.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        btnThoat_T.setkBorderRadius(20);
        btnThoat_T.setkEndColor(new java.awt.Color(0, 0, 0));
        btnThoat_T.setkForeGround(new java.awt.Color(0, 0, 0));
        btnThoat_T.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnThoat_T.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnThoat_T.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnThoat_T.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnThoat_T.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnThoat_T.setkStartColor(new java.awt.Color(0, 0, 0));
        btnThoat_T.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoat_TActionPerformed(evt);
            }
        });

        lbrMaBD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrMaBD.setForeground(new java.awt.Color(163, 200, 242));
        lbrMaBD.setText("jLabel12");

        lbrMasach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrMasach.setForeground(new java.awt.Color(163, 200, 242));
        lbrMasach.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chb6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chb5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chb3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chb4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(kGradientPanel2Layout.createSequentialGroup()
                            .addComponent(btnThoat_T, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTrasach, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(kGradientPanel2Layout.createSequentialGroup()
                            .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J1)
                                .addComponent(J2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayT, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel65)
                        .addComponent(txtMaPM_T, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel67)))
                .addGap(111, 111, 111))
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(jLabel64))
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lbrMaBD, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(lbrMasach, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbrMaBD)
                    .addComponent(lbrMasach, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabel64)
                .addGap(34, 34, 34)
                .addComponent(txtMaPM_T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chb3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chb4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chb5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chb6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addComponent(J1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J2))
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtNgayT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrasach, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat_T, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        Tra_Sach.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 510, 630));

        Menu_1.addTab("tab5", Tra_Sach);

        Phieu_Muon.setBackground(new java.awt.Color(215, 216, 219));

        Phieumuon.setBackground(new java.awt.Color(255, 255, 255));
        Phieumuon.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel45.setText("THƯ VIỆN TRƯỜNG ĐẠI HOC CÔNG NGHỆ THÔNG TIN");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel46.setText("PHIẾU MƯỢN");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel47.setText("VÀ TRUYỀN THÔNG THÁI NGUYÊN");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setText("Họ và tên:");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setText("Lớp:");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setText("Mã sách:");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel51.setText("Tên sách:");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel52.setText("Ngày mượn:");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setText("Ngày trả:");

        lbrTen_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrTen_P.setBorder(new javax.swing.border.MatteBorder(null));

        lbrLop_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrLop_P.setBorder(new javax.swing.border.MatteBorder(null));

        lbrMaS_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrMaS_P.setBorder(new javax.swing.border.MatteBorder(null));

        lbrTenS_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrTenS_P.setBorder(new javax.swing.border.MatteBorder(null));

        lbrNgayM_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrNgayM_P.setBorder(new javax.swing.border.MatteBorder(null));

        lbrNgayT_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrNgayT_P.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout PhieumuonLayout = new javax.swing.GroupLayout(Phieumuon);
        Phieumuon.setLayout(PhieumuonLayout);
        PhieumuonLayout.setHorizontalGroup(
            PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PhieumuonLayout.createSequentialGroup()
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PhieumuonLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel45))
                    .addGroup(PhieumuonLayout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addGroup(PhieumuonLayout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel46))))
                    .addGroup(PhieumuonLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52))
                        .addGap(32, 32, 32)
                        .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PhieumuonLayout.createSequentialGroup()
                                .addComponent(lbrNgayM_P, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel53)
                                .addGap(28, 28, 28)
                                .addComponent(lbrNgayT_P, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbrTenS_P, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbrMaS_P, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbrLop_P, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbrTen_P, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        PhieumuonLayout.setVerticalGroup(
            PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PhieumuonLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel46)
                .addGap(29, 29, 29)
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(lbrTen_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(lbrLop_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(lbrMaS_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(lbrTenS_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jLabel53)
                    .addComponent(lbrNgayM_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrNgayT_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        txtMaphieu_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaphieu_P.setLabelText("Mã phiếu mượn");
        txtMaphieu_P.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaphieu_PFocusLost(evt);
            }
        });

        xLoiMP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        xLoiMP.setText("   ");

        btnIn.setForeground(new java.awt.Color(0, 0, 0));
        btnIn.setText("In");
        btnIn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnIn.setkAllowGradient(false);
        btnIn.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        btnIn.setkBorderRadius(20);
        btnIn.setkEndColor(new java.awt.Color(0, 0, 0));
        btnIn.setkForeGround(new java.awt.Color(0, 0, 0));
        btnIn.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnIn.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnIn.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnIn.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnIn.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnIn.setkStartColor(new java.awt.Color(0, 0, 0));
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        btnThoat17.setForeground(new java.awt.Color(0, 0, 0));
        btnThoat17.setText("Tìm");
        btnThoat17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThoat17.setkAllowGradient(false);
        btnThoat17.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        btnThoat17.setkBorderRadius(20);
        btnThoat17.setkEndColor(new java.awt.Color(0, 0, 0));
        btnThoat17.setkForeGround(new java.awt.Color(0, 0, 0));
        btnThoat17.setkHoverColor(new java.awt.Color(204, 204, 204));
        btnThoat17.setkHoverEndColor(new java.awt.Color(0, 0, 0));
        btnThoat17.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnThoat17.setkHoverStartColor(new java.awt.Color(0, 0, 0));
        btnThoat17.setkSelectedColor(new java.awt.Color(0, 0, 0));
        btnThoat17.setkStartColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout Phieu_MuonLayout = new javax.swing.GroupLayout(Phieu_Muon);
        Phieu_Muon.setLayout(Phieu_MuonLayout);
        Phieu_MuonLayout.setHorizontalGroup(
            Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phieu_MuonLayout.createSequentialGroup()
                .addContainerGap(179, Short.MAX_VALUE)
                .addGroup(Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Phieu_MuonLayout.createSequentialGroup()
                        .addGroup(Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaphieu_P, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThoat17, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(xLoiMP)
                        .addGap(188, 188, 188)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Phieumuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(131, 131, 131))
        );
        Phieu_MuonLayout.setVerticalGroup(
            Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phieu_MuonLayout.createSequentialGroup()
                .addGroup(Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Phieu_MuonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(Phieu_MuonLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaphieu_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(xLoiMP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThoat17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(Phieumuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        Menu_1.addTab("tab6", Phieu_Muon);

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Add_BG/books-3733892_1920.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1002, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 651, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Menu_1.addTab("tab7", jPanel8);

        jPanel1.add(Menu_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, -30, 990, 670));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomePageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomePageMousePressed
        // TODO add your handling code here:
        setColor(btnHomePage);
        x1.setOpaque(true);
        resetColor(new JPanel[]{btnQlySach,btnQlyND,btnQlyM,btnQlyT,btnPhieumuon,btnDangxuat,btnThoatPM}, new JPanel[]{x2,x3,x4,x5,x6,x7,x8});
        Menu_1.setSelectedIndex(0);
    }//GEN-LAST:event_btnHomePageMousePressed

    private void btnQlySachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQlySachMousePressed
        // TODO add your handling code here:
        setColor(btnQlySach);
        x2.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlyND,btnQlyM,btnQlyT,btnPhieumuon,btnDangxuat,btnThoatPM}, new JPanel[]{x1,x3,x4,x5,x6,x7,x8});
        Menu_1.setSelectedIndex(1);
    }//GEN-LAST:event_btnQlySachMousePressed

    private void btnQlyNDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQlyNDMousePressed
        // TODO add your handling code here:
        setColor(btnQlyND);
        x3.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlySach,btnQlyM,btnQlyT,btnPhieumuon,btnDangxuat,btnThoatPM}, new JPanel[]{x1,x2,x4,x5,x6,x7,x8});
        Menu_1.setSelectedIndex(2);
    }//GEN-LAST:event_btnQlyNDMousePressed

    private void btnQlyMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQlyMMousePressed
        // TODO add your handling code here:
        setColor(btnQlyM);
        x4.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlySach,btnQlyND,btnQlyT,btnPhieumuon,btnDangxuat,btnThoatPM}, new JPanel[]{x1,x2,x3,x5,x6,x7,x8});
        Menu_1.setSelectedIndex(3);
    }//GEN-LAST:event_btnQlyMMousePressed

    private void btnQlyTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQlyTMousePressed
        // TODO add your handling code here:
        setColor(btnQlyT);
        x5.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlySach,btnQlyND,btnQlyM,btnPhieumuon,btnDangxuat,btnThoatPM}, new JPanel[]{x1,x2,x3,x4,x6,x7,x8});
        Menu_1.setSelectedIndex(4);
    }//GEN-LAST:event_btnQlyTMousePressed

    private void btnPhieumuonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPhieumuonMousePressed
        // TODO add your handling code here:
        setColor(btnPhieumuon);
        x6.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlySach,btnQlyND,btnQlyM,btnQlyT,btnDangxuat,btnThoatPM}, new JPanel[]{x1,x2,x3,x4,x5,x7,x8});
        Menu_1.setSelectedIndex(5);
    }//GEN-LAST:event_btnPhieumuonMousePressed

    private void btnDangxuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangxuatMousePressed
        // TODO add your handling code here:
        setColor(btnDangxuat);
        x7.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlySach,btnQlyND,btnQlyM,btnQlyT,btnPhieumuon,btnThoatPM}, new JPanel[]{x1,x2,x3,x4,x5,x6,x8});
        JFrame frame = new JFrame("Đăng xuất");
        if(JOptionPane.showConfirmDialog(frame,"Bạn có muốn đăng xuất không?","Đăng xuất",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        {
            frm1_Giaodienchinh fr = new frm1_Giaodienchinh();
            fr.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnDangxuatMousePressed

    private void btnThoatPMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatPMMousePressed
        // TODO add your handling code here:
        setColor(btnThoatPM);
        x8.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlySach,btnQlyND,btnQlyM,btnQlyT,btnPhieumuon,btnDangxuat}, new JPanel[]{x1,x2,x3,x4,x5,x6,x7});
        JFrame frame = new JFrame("Thoát");
        if(JOptionPane.showConfirmDialog(frame,"Bạn có muốn thoát phần mềm không?","Thoát",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_btnThoatPMMousePressed

    private void btnThemSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSActionPerformed
        // TODO add your handling code here:
        try {
        Connection con = kn.getConnection();
        String query = "insert into Sach(Masach, Tensach, Tacgia, Theloai, Khoa, Soluong) values (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, txtMasach.getText());
        ps.setString(2, txtTensach.getText());
        ps.setString(3, txtTacgia.getText());
        String Theloai="";
        if(chbGiaotrinh.isSelected()){
            Theloai += chbGiaotrinh.getText()+"";
        }
        if(chbSach.isSelected()){
            Theloai += chbSach.getText()+"";
        }
        if(chbLuanan.isSelected()){
            Theloai += chbLuanan.getText()+"";
        }   
        ps.setString(4, Theloai);
        String Khoa;
        Khoa = cbKhoa.getSelectedItem().toString();
        ps.setString(5, Khoa);
        try {
            int sl = Integer.parseInt(txtSoluong.getText());//Ép kiểu cho số để không bị sai định dạng khi nhâp vào
            ps.setInt(6, sl);
        } catch (Exception e) {
        }
        if(txtMasach.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền Mã sách");
            return;
        }else{
            if(txtTensach.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Bạn chưa điền Tên sách");
                return;
        }else{
                if(txtTacgia.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Bạn chưa điền Tác giả");
                return;
        }else{
                if(!(chbGiaotrinh.isSelected()||chbSach.isSelected()||chbLuanan.isSelected())){
                    JOptionPane.showMessageDialog(this, "Bạn chưa chọn Thể loại");
                    return;
        }else{
                if(txtSoluong.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Bạn chưa điền Số lượng");
                    return;
                }
              }
            }
          }
        }
        //ps.setString(6, txtSoluong.getText());
        ps.executeUpdate();//Update dữ liệu đã nhâp lên SQL
        //3 dòng sau để sau khi update xong thì bảng cũng sẽ tự cập nhật lại mà kh cần chạy lại phần mềm
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);
        show_Sach();
        JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
        hienThiSLHomePage();
    }//GEN-LAST:event_btnThemSActionPerformed

    private void btnSuaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSActionPerformed
        // TODO add your handling code here:
        int xn = JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa dữ liệu không?", "Sửa", JOptionPane.YES_NO_OPTION);
        if(xn==0){
        try {
        Connection con = kn.getConnection();
        int row = tblSach.getSelectedRow();
        String value = (tblSach.getModel().getValueAt(row, 0).toString());
        String query = "UPDATE Sach SET Masach=?, Tensach=?, Tacgia=?, Theloai=?, Khoa=?, Soluong=? where STT="+value ;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, txtMasach.getText());
        ps.setString(2, txtTensach.getText());
        ps.setString(3, txtTacgia.getText());
        String Theloai="";
        if(chbGiaotrinh.isSelected()){
            Theloai += chbGiaotrinh.getText()+"";
        }
        if(chbSach.isSelected()){
            Theloai += chbSach.getText()+"";
        }
        if(chbLuanan.isSelected()){
            Theloai += chbLuanan.getText()+"";
        }   
        ps.setString(4, Theloai);
        String Khoa;
        Khoa = cbKhoa.getSelectedItem().toString();
        ps.setString(5, Khoa);
        ps.setString(6, txtSoluong.getText());
        ps.executeUpdate();//Update dữ liệu SQL
        //3 dòng sau để sau khi update xong thì bảng cũng sẽ tự cập nhật lại mà kh cần chạy lại phần mềm
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);
        show_Sach();
        JOptionPane.showMessageDialog(this, "Sửa dữ liệu thành công");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }}
        hienThiSLHomePage();
    }//GEN-LAST:event_btnSuaSActionPerformed

    private void btnXoaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSActionPerformed
        // TODO add your handling code here:
        int xn = JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa dữ liệu không?", "Xoá", JOptionPane.YES_NO_OPTION);
        if(xn==0){
        try {
        Connection con = kn.getConnection();
        int row = tblSach.getSelectedRow(); //Trả về dữ liệu của dòng đang được chọn
        String value = (tblSach.getModel().getValueAt(row, 0).toString());
        String query = "DELETE FROM Sach WHERE STT= "+value;
        PreparedStatement ps = con.prepareStatement(query);
        ps.executeUpdate(); //Update dữ liệu lên SQL
        //3 dòng sau để sau khi xóa xong thì bảng cũng sẽ tự cập nhật lại mà kh cần chạy lại phần mềm
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);
        show_Sach();
        JOptionPane.showMessageDialog(this, "Xoá dữ liệu thành công");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }}
        hienThiSLHomePage();
    }//GEN-LAST:event_btnXoaSActionPerformed

    private void btnResetSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSActionPerformed
        // TODO add your handling code here:
        txtMasach.setText("");
        txtTensach.setText("");
        txtTacgia.setText("");
        buttonGroup1.clearSelection();
        cbKhoa.setSelectedIndex(-1);
        txtSoluong.setText("");
    }//GEN-LAST:event_btnResetSActionPerformed

    private void btnExit_SActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit_SActionPerformed
        // TODO add your handling code here:
        JFrame frame = new JFrame("Thoát");
        if(JOptionPane.showConfirmDialog(frame,"Bạn có muốn thoát không?","Thoát",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        {
        Menu_1.setSelectedIndex(0);
        txtMasach.setText("");
        txtTensach.setText("");
        txtTacgia.setText("");
        buttonGroup1.clearSelection();
        cbKhoa.setSelectedIndex(-1);
        txtSoluong.setText("");
        }
        setColor(btnHomePage);
        x1.setOpaque(true);
        resetColor(new JPanel[]{btnQlySach,btnQlyND,btnQlyM,btnQlyT,btnPhieumuon,btnDangxuat,btnThoatPM}, new JPanel[]{x2,x3,x4,x5,x6,x7,x8});
    }//GEN-LAST:event_btnExit_SActionPerformed

    private void txtSearchSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSKeyReleased
        // TODO add your handling code here:
        String searchString = txtSearchS.getText();
        searchS(searchString);
    }//GEN-LAST:event_txtSearchSKeyReleased

    private void tblSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMouseClicked
        // TODO add your handling code here:
        int i = tblSach.getSelectedRow();
        TableModel model = tblSach.getModel();
        
        txtMasach.setText(model.getValueAt(i, 1).toString());
        txtTensach.setText(model.getValueAt(i, 2).toString());
        txtTacgia.setText(model.getValueAt(i, 3).toString());
        String Theloai = model.getValueAt(i, 4).toString();
        if(Theloai.equals("Giáo trình")){
            chbGiaotrinh.setSelected(true);
        }
        if(Theloai.equals("Sách")){
            chbSach.setSelected(true);
        }
        if(Theloai.equals("Luận án")){
            chbLuanan.setSelected(true);
        }
        String Khoa = model.getValueAt(i, 5).toString();
            switch(Khoa){
                case"Công nghệ thông tin":
                    cbKhoa.setSelectedIndex(0);
                    break;
                case"Công nghệ điện tử và truyền thông":
                    cbKhoa.setSelectedIndex(1);
                    break;
                case"Công nghệ tự động hóa":
                    cbKhoa.setSelectedIndex(2);
                    break;
                case"Hệ thống thông tin kinh tế":
                    cbKhoa.setSelectedIndex(3);
                    break;
                case"Khoa học cơ bản":
                    cbKhoa.setSelectedIndex(4);
                    break;
                case"Truyền thông đa phương tiện":
                    cbKhoa.setSelectedIndex(5);
                    break;
            }
        txtSoluong.setText(model.getValueAt(i, 6).toString());
    }//GEN-LAST:event_tblSachMouseClicked

    private void btnThemNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNActionPerformed
        // TODO add your handling code here:
        try {
        Connection con = kn.getConnection();
        String query = "insert into TTBD(MaBD, MaSV, Lop, Khoa, Hoten, Gioitinh, Ngaysinh, SDT, Gmail, Vipham) values (?,?,?,?,?,?,?,?,?,'0')";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, txtMaBD_U.getText());
        ps.setString(2, txtMaSV.getText());
        ps.setString(3, txtLop.getText());
        String Khoa;
        Khoa = cbKhoa1.getSelectedItem().toString();
        ps.setString(4, Khoa);
        ps.setString(5, txtHoten.getText());
        String Gioitinh = "";
        if(chbNam.isSelected()){
            Gioitinh += chbNam.getText()+"";
        }else{
            Gioitinh += chbNu.getText()+"";
        }
        ps.setString(6, Gioitinh);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(cDate.getDate());
        ps.setString(7, date);
        ps.setString(8, txtSDT.getText());
        ps.setString(9, txtGmail.getText());
        ps.executeUpdate(); //Update dữ liệu lên SQL
        //3 dòng sau để sau khi thêm xong thì bảng cũng sẽ tự cập nhật lại mà kh cần chạy lại phần mềm
        DefaultTableModel model = (DefaultTableModel) tblNdung.getModel();
        model.setRowCount(0); //setRowCount(0): trả về tất cả các hàng thỏa mãn điều kiện
        show_Ndung();
        hienThiSLNHomePage();
        JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
    }//GEN-LAST:event_btnThemNActionPerformed

    private void btnSuaNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNActionPerformed
        // TODO add your handling code here:
        int xn = JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa dữ liệu không?", "Sửa", JOptionPane.YES_NO_OPTION);
        if(xn==0){
        try {
        Connection con = kn.getConnection();
        int row = tblNdung.getSelectedRow();
        String value = (tblNdung.getModel().getValueAt(row, 0).toString());
        String VP = (tblNdung.getModel().getValueAt(row, 10).toString());
        String query = "UPDATE TTBD SET MaBD=?, MaSV=?, Lop=?, Khoa=?, Hoten=?, Gioitinh=?, NgaySinh=?, SDT=?, Gmail=?, Vipham="+VP+" where STT="+value ;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, txtMaBD_U.getText());
        ps.setString(2, txtMaSV.getText());
        ps.setString(3, txtLop.getText());
        String Khoa;
        Khoa = cbKhoa.getSelectedItem().toString();
        ps.setString(4, Khoa);
        ps.setString(5, txtHoten.getText());
        String Gioitinh = "";
        if(chbNam.isSelected()){
            Gioitinh += chbNam.getText()+"";
        }else{
            Gioitinh += chbNu.getText()+"";
        }
        ps.setString(6, Gioitinh);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(cDate.getDate());
        ps.setString(7, date);
        ps.setString(8, txtSDT.getText());
        ps.setString(9, txtGmail.getText());
        ps.executeUpdate();
        DefaultTableModel model = (DefaultTableModel) tblNdung.getModel();//Lệnh để sau khi update data lên thì hiển thị luôn trên table
        model.setRowCount(0);//Lệnh để sau khi update data lên thì hiển thị luôn trên table
        show_Ndung();//Lệnh để sau khi update data lên thì hiển thị luôn trên table
        hienThiSLNHomePage();
        JOptionPane.showMessageDialog(this, "Sửa dữ liệu thành công");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }}
    }//GEN-LAST:event_btnSuaNActionPerformed

    private void tblNdungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNdungMouseClicked
        // TODO add your handling code here:
        int i = tblNdung.getSelectedRow();
        TableModel model = tblNdung.getModel();
        txtMaBD_U.setText(model.getValueAt(i, 1).toString());
        txtMaSV.setText(model.getValueAt(i, 2).toString());
        txtLop.setText(model.getValueAt(i, 3).toString());
        String Khoa = model.getValueAt(i, 4).toString();
            switch(Khoa){
                case"Công nghệ thông tin":
                    cbKhoa1.setSelectedIndex(0);
                    break;
                case"Công nghệ điện tử và truyền thông":
                    cbKhoa1.setSelectedIndex(1);
                    break;
                case"Công nghệ tự động hóa":
                    cbKhoa1.setSelectedIndex(2);
                    break;
                case"Hệ thống thông tin kinh tế":
                    cbKhoa1.setSelectedIndex(3);
                    break;
                case"Khoa học cơ bản":
                    cbKhoa1.setSelectedIndex(4);
                    break;
                case"Truyền thông đa phương tiện":
                    cbKhoa1.setSelectedIndex(5);
                    break;
            }
        txtHoten.setText(model.getValueAt(i, 5).toString());
        String Gioitinh = model.getValueAt(i, 6).toString();
        if(Gioitinh.equals("Nam")){
            chbNam.setSelected(true);
        }else{
            chbNu.setSelected(true);
        }
        try {
            int srow = tblNdung.getSelectedRow();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(srow, 7));
            cDate.setDate(date);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        txtSDT.setText(model.getValueAt(i, 8).toString());
        txtGmail.setText(model.getValueAt(i, 9).toString());
    }//GEN-LAST:event_tblNdungMouseClicked

    private void btnXoaNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNActionPerformed
        // TODO add your handling code here:
        int xn = JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa dữ liệu không?", "Xoá", JOptionPane.YES_NO_OPTION);
        if(xn==0){
        try {
        Connection con = kn.getConnection();
        int row = tblNdung.getSelectedRow();
        String value = (tblNdung.getModel().getValueAt(row, 0).toString());
        String query = "DELETE FROM TTBD WHERE STT= "+value;
        PreparedStatement ps = con.prepareStatement(query);
        ps.executeUpdate();
        //3 dòng sau để sau khi xóa xong thì bảng cũng sẽ tự cập nhật lại mà kh cần chạy lại phần mềm
        DefaultTableModel model = (DefaultTableModel) tblNdung.getModel();
        model.setRowCount(0);
        show_Ndung();
        hienThiSLNHomePage();
        JOptionPane.showMessageDialog(this, "Xoá dữ liệu thành công");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }}
    }//GEN-LAST:event_btnXoaNActionPerformed

    private void btnResetNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetNActionPerformed
        // TODO add your handling code here:
        txtMaBD_U.setText("");
        txtMaSV.setText("");
        txtLop.setText("");
        cbKhoa1.setSelectedIndex(-1);
        txtGmail.setText("");
        txtHoten.setText("");
        buttonGroup2.clearSelection();
        //cDate.setEnabled(rootPaneCheckingEnabled);
        txtSDT.setText("");
    }//GEN-LAST:event_btnResetNActionPerformed

    private void btnExitNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitNActionPerformed
        // TODO add your handling code here:
        JFrame frame = new JFrame("Thoát");
        if(JOptionPane.showConfirmDialog(frame,"Bạn có muốn thoát không?","Thoát",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        {
        Menu_1.setSelectedIndex(0);
        txtMaBD_U.setText("");
        txtMaSV.setText("");
        txtLop.setText("");
        cbKhoa1.setSelectedIndex(0);
        txtGmail.setText("");
        txtHoten.setText("");
        buttonGroup2.clearSelection();
        //cDate.removeAll();
        txtSDT.setText("");
        }
        setColor(btnHomePage);
        x1.setOpaque(true);
        resetColor(new JPanel[]{btnQlySach,btnQlyND,btnQlyM,btnQlyT,btnPhieumuon,btnDangxuat,btnThoatPM}, new JPanel[]{x2,x3,x4,x5,x6,x7,x8});
    }//GEN-LAST:event_btnExitNActionPerformed

    private void txtSearchNDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchNDKeyReleased
        // TODO add your handling code here:
        String searchString = txtSearchND.getText();
        searchN(searchString);
    }//GEN-LAST:event_txtSearchNDKeyReleased

    private void btnChomuon_MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChomuon_MActionPerformed
        // TODO add your handling code here:
        int SL1 = Integer.parseInt(txtSL_M.getText());
        int SL2 = Integer.parseInt(lbrSoluong_M.getText());
        int VP = Integer.parseInt(lbrVP_M.getText());
        String Loi1 = lbrLoiMS.getText();
        String Loi2 = lbrLoiBD.getText();
        String t1 = "Mã sách không khớp!";
        String t2 = "Mã bạn đọc không khớp!";
        if(VP >= 3){
            JOptionPane.showMessageDialog(this, "Bạn đọc này đã bị cấm mượn sách");
            return;
        }else{
            if(lbrSoluong_M.getText().equals("0")){
            JOptionPane.showMessageDialog(this, "Số lượng sách trong kho đã hết");
            return;
        }else{
            if(SL1 > SL2){
            JOptionPane.showMessageDialog(this, "Số lượng sách không hợp lệ");
            return;
        }else{
            if(SL1 <= 0){
            JOptionPane.showMessageDialog(this, "Số lượng sách không hợp lệ");
            return;
        }else{
            try {
                Connection con = kn.getConnection();
                String MuonS = "insert into MuonS(MaPM, Masach, TenSach, MaBD, TenBD, Lop, NgayM, NgayT, Soluong, TLoai, Ghichu) values (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(MuonS);
                ps.setString(1,txtMaPM_M.getText());
                ps.setString(2, txtMasach_M.getText());
                ps.setString(3, lbrTensach_M.getText());
                ps.setString(4, txtMaBD_M.getText());
                ps.setString(5, lbrTenSV_M.getText());
                ps.setString(6, lbrLop_M.getText());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                if(chb1.isSelected()){
                    Date d = new Date();
                    String dd = df.format(d);
                    ps.setString(7, dd);
                    ps.setString(8,dd);
                }else{
                    String dateM = df.format(txtNgayM_M.getDate());
                    ps.setString(7, dateM);
                    String dateT = df.format(txtNgayT_M.getDate());
                    ps.setString(8, dateT);
                }
                try {
                    int sl = Integer.parseInt(txtSL_M.getText());//Ép kiểu cho số để không bị sai định dạng khi nhâp vào
                    ps.setInt(9, sl);
                } catch (Exception e) {
                }
                String TLoai = "";
                if(chb1.isSelected()){
                    TLoai += chb1.getText();
                }else{
                    TLoai += chb2.getText();
                }
                ps.setString(10, TLoai);
                ps.setString(11, "Mượn");
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Cho mượn thành công");
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            UpdateSL_M();
            DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
            model.setRowCount(0);
            show_Sach();
            hienThiSLHomePage();
            hienThiSachdangmuon();
           }
          }
         }
       }
    }//GEN-LAST:event_btnChomuon_MActionPerformed

    private void btnThoat_MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoat_MActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnThoat_MActionPerformed

    private void chb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb1ActionPerformed
        // TODO add your handling code here:
        lbNgayM.setVisible(false);
        txtNgayM_M.setVisible(false);
        lbNgayT.setVisible(false);
        txtNgayT_M.setVisible(false);
    }//GEN-LAST:event_chb1ActionPerformed

    private void chb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb2ActionPerformed
        // TODO add your handling code here:
        lbNgayM.setVisible(true);
        txtNgayM_M.setVisible(true);
        lbNgayT.setVisible(true);
        txtNgayT_M.setVisible(true);
    }//GEN-LAST:event_chb2ActionPerformed

    private void txtMasach_MFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMasach_MFocusLost
        // TODO add your handling code here:
        if(!txtMasach_M.getText().equals("")){
            hienthiTTS_M();
        }
    }//GEN-LAST:event_txtMasach_MFocusLost

    private void txtMaBD_MFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaBD_MFocusLost
        // TODO add your handling code here:
        if(!txtMaBD_M.getText().equals("")){
            hienthiTTBD_M();
        }
    }//GEN-LAST:event_txtMaBD_MFocusLost

    private void btnTrasachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrasachActionPerformed
        // TODO add your handling code here:
        try {
            Connection con = kn.getConnection();
            String TraS = "insert into TraS(MaPM, TenSV, Lop, Tensach, Soluong, NgayM, NgayT, NgayT_TT, TinhtrangS, Ghichu) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(TraS);
            ps.setString(1, txtMaPM_T.getText());
            ps.setString(2, lbrTenSV_T.getText());
            ps.setString(3, lbrLop_T.getText());
            ps.setString(4, lbrTensach_T.getText());
            ps.setString(5, lbrSoluong_T.getText());
            ps.setString(6, lbrNgayM_T.getText());
            ps.setString(7, lbrNgayT_T.getText());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if(chb5.isSelected()){
                Date d = new Date();
                String dd = df.format(d);
                ps.setString(8, dd);
            }else{
                String date = df.format(txtNgayT.getDate());
                ps.setString(8, date);
            }
            String TinhtrangS = "";
            if(chb1.isSelected()){
            TinhtrangS += chb1.getText();
            }else{
            TinhtrangS += chb2.getText();
            }
            ps.setString(9, TinhtrangS);
            String ghichu = "";
            if(chb3.isSelected()){
                ghichu += chb3.getText();
            }else{
                ghichu += chb4.getText();
            }
            ps.setString(10, ghichu);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Tra sach thanh cong");
        } catch (Exception e) {
        }
        UpdateSL_T();
        Update_Vipham();
        DefaultTableModel model = (DefaultTableModel) tblNdung.getModel();
        model.setRowCount(0);
        show_Ndung();
        hienThiSLHomePage();
        hienThiSachdangmuon();
    }//GEN-LAST:event_btnTrasachActionPerformed

    private void chb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb5ActionPerformed
        // TODO add your handling code here:
        J1.setVisible(false);
        J2.setVisible(false);
        txtNgayT.setVisible(false);
    }//GEN-LAST:event_chb5ActionPerformed

    private void chb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb6ActionPerformed
        // TODO add your handling code here:
        J1.setVisible(true);
        J2.setVisible(true);
        txtNgayT.setVisible(true);
    }//GEN-LAST:event_chb6ActionPerformed

    private void txtMaPM_TFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaPM_TFocusLost
        // TODO add your handling code here:
        if(!txtMaPM_T.getText().equals("")){
            hienthiThongtinTra();
        }
    }//GEN-LAST:event_txtMaPM_TFocusLost

    private void btnThoat_TActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoat_TActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnThoat_TActionPerformed

    private void txtMaphieu_PFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaphieu_PFocusLost
        // TODO add your handling code here:
        if(!txtMaphieu_P.getText().equals("")){
            hienthiPhieuMuon();
        }
    }//GEN-LAST:event_txtMaphieu_PFocusLost

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO add your handling code here:
        PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("In phiếu mượn");
            
            job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                    pf.setOrientation(PageFormat.LANDSCAPE);
                 if(pageNum > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.47,0.47);
                
                Phieumuon.print(g2);
         
               
                return Printable.PAGE_EXISTS;
                         
                
            }
    });
            boolean ok = job.printDialog();
        if(ok){
        try{
            
        job.print();
        }
        catch (PrinterException ex){
	ex.printStackTrace();
}
        }
    }//GEN-LAST:event_btnInActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm2_Thuthu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm2_Thuthu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm2_Thuthu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm2_Thuthu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm2_Thuthu().setVisible(true);
            }
        });
    }
    
   private void setColor(JPanel pane){
       pane.setBackground(new Color(29,38,40));
   }
   
   private void resetColor(JPanel[] pane , JPanel[] x){
       for (int i = 0; i < pane.length; i++) {
           pane[i].setBackground(new Color(36,46,54));
           
       }
       for (int i = 0; i < pane.length; i++) {
           x[i].setOpaque(false);
           
       }
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Home_Page;
    private javax.swing.JLabel J1;
    private javax.swing.JLabel J2;
    private javax.swing.JPanel Menu;
    private javax.swing.JTabbedPane Menu_1;
    private javax.swing.JPanel Muon_Sach;
    private javax.swing.JPanel Phieu_Muon;
    private com.k33ptoo.components.KGradientPanel Phieu_muon;
    private javax.swing.JPanel Phieumuon;
    private javax.swing.JPanel Qly_NguoiDoc;
    private javax.swing.JPanel Qly_Sach;
    private javax.swing.JPanel Sach;
    private javax.swing.JPanel Sinh_vien;
    private javax.swing.JPanel Tra_Sach;
    private com.k33ptoo.components.KButton btnChomuon_M;
    private javax.swing.JPanel btnDangxuat;
    private com.k33ptoo.components.KButton btnExitN;
    private com.k33ptoo.components.KButton btnExit_S;
    private javax.swing.JPanel btnHomePage;
    private com.k33ptoo.components.KButton btnIn;
    private javax.swing.JPanel btnPhieumuon;
    private javax.swing.JPanel btnQlyM;
    private javax.swing.JPanel btnQlyND;
    private javax.swing.JPanel btnQlySach;
    private javax.swing.JPanel btnQlyT;
    private com.k33ptoo.components.KButton btnResetN;
    private com.k33ptoo.components.KButton btnResetS;
    private com.k33ptoo.components.KButton btnSuaN;
    private com.k33ptoo.components.KButton btnSuaS;
    private com.k33ptoo.components.KButton btnThemN;
    private com.k33ptoo.components.KButton btnThemS;
    private com.k33ptoo.components.KButton btnThoat17;
    private javax.swing.JPanel btnThoatPM;
    private com.k33ptoo.components.KButton btnThoat_M;
    private com.k33ptoo.components.KButton btnThoat_T;
    private com.k33ptoo.components.KButton btnTrasach;
    private com.k33ptoo.components.KButton btnXoaN;
    private com.k33ptoo.components.KButton btnXoaS;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private com.toedter.calendar.JDateChooser cDate;
    private combobox.Combobox cbKhoa;
    private combobox.Combobox cbKhoa1;
    private checkbox.JCheckBoxCustom chb1;
    private checkbox.JCheckBoxCustom chb2;
    private checkbox.JCheckBoxCustom chb3;
    private checkbox.JCheckBoxCustom chb4;
    private checkbox.JCheckBoxCustom chb5;
    private checkbox.JCheckBoxCustom chb6;
    private checkbox.JCheckBoxCustom_1 chbGiaotrinh;
    private checkbox.JCheckBoxCustom_1 chbLuanan;
    private checkbox.JCheckBoxCustom_1 chbNam;
    private checkbox.JCheckBoxCustom_1 chbNu;
    private checkbox.JCheckBoxCustom_1 chbSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.k33ptoo.components.KGradientPanel kGradientPanel2;
    private javax.swing.JLabel lbNgayM;
    private javax.swing.JLabel lbNgayT;
    private javax.swing.JLabel lbrLoiBD;
    private javax.swing.JLabel lbrLoiMS;
    private javax.swing.JLabel lbrLoiMS1;
    private javax.swing.JLabel lbrLop_M;
    private javax.swing.JTextField lbrLop_P;
    private javax.swing.JLabel lbrLop_T;
    private javax.swing.JLabel lbrMaBD;
    private javax.swing.JLabel lbrMaBD_M;
    private javax.swing.JTextField lbrMaS_P;
    private javax.swing.JLabel lbrMasach;
    private javax.swing.JLabel lbrMasach_M;
    private javax.swing.JTextField lbrNgayM_P;
    private javax.swing.JLabel lbrNgayM_T;
    private javax.swing.JTextField lbrNgayT_P;
    private javax.swing.JLabel lbrNgayT_T;
    private javax.swing.JLabel lbrSDM_H;
    private javax.swing.JLabel lbrSDT_M;
    private javax.swing.JLabel lbrSLBD_H;
    private javax.swing.JLabel lbrSLSach_H;
    private javax.swing.JLabel lbrSoluong_M;
    private javax.swing.JLabel lbrSoluong_T;
    private javax.swing.JLabel lbrTacgia_M;
    private javax.swing.JLabel lbrTenSV_M;
    private javax.swing.JLabel lbrTenSV_T;
    private javax.swing.JTextField lbrTenS_P;
    private javax.swing.JTextField lbrTen_P;
    private javax.swing.JLabel lbrTensach_M;
    private javax.swing.JLabel lbrTensach_T;
    private javax.swing.JLabel lbrVP_M;
    private PM_QlyThuVien.PanelRound panelRound11;
    private PM_QlyThuVien.PanelRound panelRound12;
    private PM_QlyThuVien.PanelRound panelRound13;
    private PM_QlyThuVien.PanelRound panelRound2;
    private PM_QlyThuVien.PanelRound panelRound4;
    private PM_QlyThuVien.PanelRound panelRound5;
    private PM_QlyThuVien.PanelRound panelRound6;
    private PM_QlyThuVien.PanelRound panelRound7;
    private PM_QlyThuVien.PanelRound panelRound8;
    private javaswingdev.swing.table.Table tblNdung;
    private javaswingdev.swing.table.Table tblSach;
    private PM_QlyThuVien.TextField txtGmail;
    private PM_QlyThuVien.TextField txtHoten;
    private PM_QlyThuVien.TextField txtLop;
    private PM_QlyThuVien.TextField txtMaBD_M;
    private PM_QlyThuVien.TextField txtMaBD_U;
    private PM_QlyThuVien.TextField txtMaPM_M;
    private PM_QlyThuVien.TextField txtMaPM_T;
    private PM_QlyThuVien.TextField txtMaSV;
    private PM_QlyThuVien.TextField txtMaphieu_P;
    private PM_QlyThuVien.TextField txtMasach;
    private PM_QlyThuVien.TextField txtMasach_M;
    private com.toedter.calendar.JDateChooser txtNgayM_M;
    private com.toedter.calendar.JDateChooser txtNgayT;
    private com.toedter.calendar.JDateChooser txtNgayT_M;
    private PM_QlyThuVien.TextField txtSDT;
    private PM_QlyThuVien.TextField txtSL_M;
    private javax.swing.JTextField txtSearchND;
    private javax.swing.JTextField txtSearchS;
    private PM_QlyThuVien.TextField txtSoluong;
    private PM_QlyThuVien.TextField txtTacgia;
    private PM_QlyThuVien.TextField txtTensach;
    private javax.swing.JPanel x1;
    private javax.swing.JPanel x2;
    private javax.swing.JPanel x3;
    private javax.swing.JPanel x4;
    private javax.swing.JPanel x5;
    private javax.swing.JPanel x6;
    private javax.swing.JPanel x7;
    private javax.swing.JPanel x8;
    private javax.swing.JLabel xLoiMP;
    // End of variables declaration//GEN-END:variables
}
