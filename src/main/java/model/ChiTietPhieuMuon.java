package model;

public class ChiTietPhieuMuon {
    private int maChiTiet;
    private int maPhieuMuon;
    private int maSach;
    private int soLuong;
    private String tinhTrang;

    public ChiTietPhieuMuon() {
    }

    public ChiTietPhieuMuon(int maPhieuMuon, int maSach, int soLuong, String tinhTrang) {
        this.maPhieuMuon = maPhieuMuon;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
    }

    public ChiTietPhieuMuon(int maChiTiet, int maPhieuMuon, int maSach, int soLuong, String tinhTrang) {
        this.maChiTiet = maChiTiet;
        this.maPhieuMuon = maPhieuMuon;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
    }

    // Getters & Setters
    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuMuon{" + "maChiTiet=" + maChiTiet + ", maPhieuMuon=" + maPhieuMuon + ", maSach=" + maSach + ", soLuong=" + soLuong + ", tinhTrang=" + tinhTrang + '}';
    }
}