import java.awt.*;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GiaoDienLogin {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JPasswordField passwordField;
    private JLabel lblNewLabel_2;

    /**
     * Launch the application.
     */

    public static void main(String[] args) {
        GiaoDienLogin windowLogin = new GiaoDienLogin();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    windowLogin.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GiaoDienLogin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 24));
        frame.setBounds(100, 100, 726, 523);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        textField_1 = new JTextField();
        textField_1.setBounds(183, 162, 249, 26);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(183, 215, 249, 26);
        frame.getContentPane().add(passwordField);

        JLabel lblNewLabel = new JLabel("USERNAME");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(10, 162, 135, 26);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("PASSWORD");
        lblNewLabel_1.setForeground(Color.RED);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setBounds(10, 215, 135, 26);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel thongbao = new JLabel("Tên đăng nhập hoặc mật khẩu không đúng!");
        thongbao.setFont(new Font("Tahoma", Font.BOLD, 13));
        thongbao.setBounds(183, 251, 2000, 26);
        thongbao.setForeground(Color.RED);
        thongbao.setVisible(false);
        frame.getContentPane().add(thongbao);


        Image img= new ImageIcon(this.getClass().getResource("Test.png")).getImage().getScaledInstance(236, 151, Image.SCALE_SMOOTH);
        lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setBounds(40, 299, 236, 151);
        lblNewLabel_2.setIcon(new ImageIcon(img));
        frame.getContentPane().add(lblNewLabel_2);


        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLogin.setForeground(Color.RED);
        btnLogin.setBackground(Color.YELLOW);
        btnLogin.setBounds(538, 162, 130, 79);
        frame.getContentPane().add(btnLogin);

        JLabel lblNewLabel_3 = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ NHÂN VIÊN");
        lblNewLabel_3.setForeground(Color.BLUE);
        lblNewLabel_3.setBackground(new Color(0, 0, 255));
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_3.setBounds(165, 36, 433, 45);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Đàm Tiến Đạt-20198212");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        lblNewLabel_4.setBounds(538, 463, 164, 13);
        frame.getContentPane().add(lblNewLabel_4);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    // KIỂM TRA ĐĂNG NHẬP LOGIN
                    // DÒNG TẠO KẾT NỐI
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test quản lý sinh viên", "root", "");
                    String sql="SELECT *  FROM `quan ly tai khoan` WHERE username=? AND password=?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1,textField_1.getText());
                    ps.setString(2,passwordField.getText());
                    ResultSet rs =ps.executeQuery();
                    if(rs.next()){
//                        JOptionPane.showMessageDialog(btnLogin,"Đăng nhập thành công ");
                        GiaoDien window = new GiaoDien();
                        window.frame.setVisible(true);

                     frame.setVisible(false);

                    }
                    else  thongbao.setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });



    }
}
