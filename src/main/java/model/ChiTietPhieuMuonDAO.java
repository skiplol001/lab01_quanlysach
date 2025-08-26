package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class ChiTietPhieuMuonDAO {

    // Lấy chi tiết phiếu mượn theo mã phiếu
    public static List<ChiTietPhieuMuon> getByMaPhieuMuon(int maPhieuMuon) {
        List<ChiTietPhieuMuon> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietPhieuMuon WHERE maPhieuMuon = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhieuMuon);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon(
                        rs.getInt("maChiTiet"),
                        rs.getInt("maPhieuMuon"),
                        rs.getInt("maSach"),
                        rs.getInt("soLuong"),
                        rs.getString("tinhTrang")
                    );
                    list.add(ctpm);
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy chi tiết phiếu mượn:");
            e.printStackTrace();
        }

        return list;
    }

    // Thêm chi tiết phiếu mượn
    public boolean insertChiTietPhieuMuon(ChiTietPhieuMuon ctpm) {
        String sql = "INSERT INTO ChiTietPhieuMuon(maPhieuMuon, maSach, soLuong, tinhTrang) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ctpm.getMaPhieuMuon());
            ps.setInt(2, ctpm.getMaSach());
            ps.setInt(3, ctpm.getSoLuong());
            ps.setString(4, ctpm.getTinhTrang());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm chi tiết phiếu mượn:");
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật chi tiết phiếu mượn
    public boolean updateChiTietPhieuMuon(ChiTietPhieuMuon ctpm) {
        String sql = "UPDATE ChiTietPhieuMuon SET maSach = ?, soLuong = ?, tinhTrang = ? WHERE maChiTiet = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ctpm.getMaSach());
            ps.setInt(2, ctpm.getSoLuong());
            ps.setString(3, ctpm.getTinhTrang());
            ps.setInt(4, ctpm.getMaChiTiet());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật chi tiết phiếu mượn:");
            e.printStackTrace();
            return false;
        }
    }

    // Xóa chi tiết phiếu mượn
    public boolean deleteChiTietPhieuMuon(int maChiTiet) {
        String sql = "DELETE FROM ChiTietPhieuMuon WHERE maChiTiet = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maChiTiet);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa chi tiết phiếu mượn:");
            e.printStackTrace();
            return false;
        }
    }
}