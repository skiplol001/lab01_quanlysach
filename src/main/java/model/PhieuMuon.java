package model;

import java.sql.Date;
import java.util.List;

public class PhieuMuon {
    private int maPhieuMuon;
    private int maDocGia;
    private Date ngayMuon;
    private Date ngayTra;
    private String trangThai;
    private List<ChiTietPhieuMuon> chiTietPhieuMuonList;

    public PhieuMuon() {
    }

    public PhieuMuon(int maDocGia, Date ngayMuon, Date ngayTra, String trangThai) {
        this.maDocGia = maDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

    public PhieuMuon(int maPhieuMuon, int maDocGia, Date ngayMuon, Date ngayTra, String trangThai) {
        this.maPhieuMuon = maPhieuMuon;
        this.maDocGia = maDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

    // Getters & Setters
    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public int getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(int maDocGia) {
        this.maDocGia = maDocGia;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<ChiTietPhieuMuon> getChiTietPhieuMuonList() {
        return chiTietPhieuMuonList;
    }

    public void setChiTietPhieuMuonList(List<ChiTietPhieuMuon> chiTietPhieuMuonList) {
        this.chiTietPhieuMuonList = chiTietPhieuMuonList;
    }

    @Override
    public String toString() {
        return "PhieuMuon{" + "maPhieuMuon=" + maPhieuMuon + ", maDocGia=" + maDocGia + ", ngayMuon=" + ngayMuon + ", ngayTra=" + ngayTra + ", trangThai=" + trangThai + '}';
    }
}