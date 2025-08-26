package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class PhieuMuonDAO {

    // Lấy toàn bộ danh sách phiếu mượn
    public static List<PhieuMuon> getAll() {
        List<PhieuMuon> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuMuon";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                PhieuMuon pm = new PhieuMuon(
                    rs.getInt("maPhieuMuon"),
                    rs.getInt("maDocGia"),
                    rs.getDate("ngayMuon"),
                    rs.getDate("ngayTra"),
                    rs.getString("trangThai")
                );
                list.add(pm);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách phiếu mượn:");
            e.printStackTrace();
        }

        return list;
    }

    // Thêm phiếu mượn mới
    public int insertPhieuMuon(PhieuMuon pm) {
        String sql = "INSERT INTO PhieuMuon(maDocGia, ngayMuon, ngayTra, trangThai) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, pm.getMaDocGia());
            ps.setDate(2, new java.sql.Date(pm.getNgayMuon().getTime()));
            ps.setDate(3, new java.sql.Date(pm.getNgayTra().getTime()));
            ps.setString(4, pm.getTrangThai());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Trả về mã phiếu mượn được sinh tự động
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm phiếu mượn:");
            e.printStackTrace();
        }

        return -1;
    }

    // Cập nhật phiếu mượn
    public boolean updatePhieuMuon(PhieuMuon pm) {
        String sql = "UPDATE PhieuMuon SET maDocGia = ?, ngayMuon = ?, ngayTra = ?, trangThai = ? WHERE maPhieuMuon = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pm.getMaDocGia());
            ps.setDate(2, new java.sql.Date(pm.getNgayMuon().getTime()));
            ps.setDate(3, new java.sql.Date(pm.getNgayTra().getTime()));
            ps.setString(4, pm.getTrangThai());
            ps.setInt(5, pm.getMaPhieuMuon());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật phiếu mượn:");
            e.printStackTrace();
            return false;
        }
    }

    // Xóa phiếu mượn
    public boolean deletePhieuMuon(int maPhieuMuon) {
        String sql = "DELETE FROM PhieuMuon WHERE maPhieuMuon = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPhieuMuon);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa phiếu mượn:");
            e.printStackTrace();
            return false;
        }
    }

    // Tìm phiếu mượn theo mã độc giả
    public List<PhieuMuon> searchByMaDocGia(int maDocGia) {
        List<PhieuMuon> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuMuon WHERE maDocGia = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDocGia);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PhieuMuon pm = new PhieuMuon(
                        rs.getInt("maPhieuMuon"),
                        rs.getInt("maDocGia"),
                        rs.getDate("ngayMuon"),
                        rs.getDate("ngayTra"),
                        rs.getString("trangThai")
                    );
                    list.add(pm);
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm phiếu mượn theo mã độc giả:");
            e.printStackTrace();
        }

        return list;
    }

    // Lấy phiếu mượn theo mã
    public PhieuMuon getById(int maPhieuMuon) {
        String sql = "SELECT * FROM PhieuMuon WHERE maPhieuMuon = ?";
        PhieuMuon pm = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhieuMuon);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pm = new PhieuMuon(
                        rs.getInt("maPhieuMuon"),
                        rs.getInt("maDocGia"),
                        rs.getDate("ngayMuon"),
                        rs.getDate("ngayTra"),
                        rs.getString("trangThai")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy phiếu mượn theo mã:");
            e.printStackTrace();
        }

        return pm;
    }
}