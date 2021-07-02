import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.sql.*;
import javax.swing.JRadioButton;

public class GiaoDien {

    public JFrame frame;
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtAge;
    private JTextField txtEmail;
    private JTextField txtSalary;
    private JTable tblEmployees;
    private static boolean isEditMode = false;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GiaoDien window = new GiaoDien();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GiaoDien() {

        initialize();
        initTable();
        initEmployeeData();
    }


    private DefaultTableModel tblModel = new DefaultTableModel();


    public void initTable() {

        tblModel.setColumnIdentifiers(new Object[]{"Mã", "Họ và tên", "Tuổi", "Email", "Lương"});


        tblEmployees.setModel(tblModel);
    }

    employeeList empList = new employeeList();

    private void initEmployeeData() {
        Statement st;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test quản lý sinh viên", "root", "");
            String sql = "SELECT *  FROM `quan ly nhan vien`";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                empList.add(new employee(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getDouble("salary"), rs.getInt("age")));
            }


            empList.renderToTable(tblModel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 613, 632);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel lblNewLabel = new JLabel("QU\u1EA2N L\u00DD NH\u00C2N VI\u00CAN");
        lblNewLabel.setBounds(182, 10, 228, 46);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBounds(10, 50, 579, 535);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JSeparator separator = new JSeparator();
        separator.setBackground(Color.WHITE);
        separator.setForeground(Color.BLACK);
        separator.setBounds(0, 0, 579, 2);
        panel.add(separator);

        JLabel lblNewLabel_1 = new JLabel("M\u00E3 nh\u00E2n vi\u00EAn");
        lblNewLabel_1.setBounds(10, 33, 88, 24);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("H\u1ECD v\u00E0 t\u00EAn");
        lblNewLabel_1_1.setBounds(10, 67, 88, 24);
        panel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Tu\u1ED5i");
        lblNewLabel_1_2.setBounds(10, 101, 88, 24);
        panel.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel("Email");
        lblNewLabel_1_3.setBounds(10, 135, 88, 24);
        panel.add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_4 = new JLabel("L\u01B0\u01A1ng");
        lblNewLabel_1_4.setBounds(10, 169, 88, 24);
        panel.add(lblNewLabel_1_4);

        txtID = new JTextField();
        txtID.setBounds(95, 34, 247, 24);
        panel.add(txtID);
        txtID.setColumns(10);
        txtID.setEnabled(false);

        txtName = new JTextField();
        txtName.setColumns(10);
        txtName.setBounds(95, 68, 247, 24);
        panel.add(txtName);

        txtAge = new JTextField();
        txtAge.setColumns(10);
        txtAge.setBounds(95, 102, 247, 24);
        panel.add(txtAge);

        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setBounds(95, 136, 247, 24);
        panel.add(txtEmail);

        txtSalary = new JTextField();
        txtSalary.setColumns(10);
        txtSalary.setBounds(95, 170, 247, 24);
        panel.add(txtSalary);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(364, 22, 165, 186);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JButton btnNew = new JButton("Mới");
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtID.setText("");
                txtAge.setText("");
                txtSalary.setText("");
                txtEmail.setText("");
                isEditMode = false;
                txtID.setEnabled(true);
            }
        });
        btnNew.setIcon(null);
        btnNew.setBounds(32, 0, 85, 21);
        panel_1.add(btnNew);

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement st;

                try {
                    employee emp = new employee();
                    emp.setId(txtID.getText());
                    emp.setName(txtName.getText());
                    emp.setAge(Integer.parseInt(txtAge.getText()));
                    emp.setEmail(txtEmail.getText());
                    emp.setSalary(Float.parseFloat(txtSalary.getText()));
                    if (isEditMode == true) {


                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test quản lý sinh viên", "root", "");
                        String sql = "UPDATE `quan ly nhan vien` SET `name`=?,`age`=?,`email`=?," +
                                "`salary`=? WHERE id=?";

                        st = con.prepareStatement(sql);

                        st.setString(1, emp.getName());
                        st.setInt(2, emp.getAge());
                        st.setString(3, emp.getEmail());
                        st.setDouble(4, emp.getSalary());
                        st.setString(5, emp.getId());
                        st.execute();
                        empList.update(emp);
                    } else {
                        if (empList.findById(emp.getId()) != null) {
                            JOptionPane.showMessageDialog(btnSave, "ID is existed and can not create new emplyee");
                            return;
                        }
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test quản lý sinh viên", "root", "");
                        String sql = "INSERT INTO `quan ly nhan vien`(`id`, `name`, `age`, `email`, `salary`)     " +
                                "VALUES (?,?,?,?,?)";
                        st = con.prepareStatement(sql);
                        st.setString(1, emp.getId());
                        st.setString(2, emp.getName());
                        st.setInt(3, emp.getAge());
                        st.setString(4, emp.getEmail());
                        st.setDouble(5, emp.getSalary());
                        st.execute();
                        txtID.setEnabled(false);
                        empList.add(emp);
                    }

                    empList.renderToTable(tblModel);
                    JOptionPane.showMessageDialog(btnSave, "Employee saved");


                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(btnSave, "Error: " + E.getMessage());
                }
            }
        });
        btnSave.setBounds(32, 31, 85, 21);
        panel_1.add(btnSave);

        JButton btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement st;
                try {
                    boolean isOk = empList.deleteById(txtID.getText());
                    if (isOk) {
                        JOptionPane.showMessageDialog(btnDelete, "Employee deleted");
                        empList.renderToTable(tblModel);
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test quản lý sinh viên", "root", "");
                        String sql = "DELETE FROM `quan ly nhan vien` WHERE id=?";
                        st = con.prepareStatement(sql);
                        st.setString(1, txtID.getText());
                        st.execute();

                    } else JOptionPane.showMessageDialog(btnDelete, "this ID is not existed or delete fail!");


                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(btnDelete, "Error: " + E.getMessage());
                }
            }
        });

        btnDelete.setBounds(32, 65, 85, 21);
        panel_1.add(btnDelete);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(20, 212, 559, 46);
        panel.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblStatus = new JLabel("");
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setBounds(284, 22, 106, 24);
        panel_2.add(lblStatus);

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(Color.BLACK);
        separator_1.setBackground(Color.WHITE);
        separator_1.setBounds(0, 10, 546, 2);
        panel_2.add(separator_1);

        JButton btnFind = new JButton("Tìm");
        btnFind.addActionListener(new ActionListener() {
            //nut Save thanh nut kieu Cap nhat chu ko phai them moi
            //private boolean isEditMode = false;

            public void actionPerformed(ActionEvent e) {
                try {
                    employee emp1 = new employee();
                    employee emp2 = new employee();

                    if (txtID.getText() != null) {
                        emp1 = empList.findById(txtID.getText());
                        printInfoEmp(emp1);
                        lblStatus.setText(empList.getEmployeeInfo(emp1));
                    }
                    if (emp1 == null )
                        JOptionPane.showMessageDialog(btnFind, "this ID/Name does not exist");
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(btnFind, "Error: " + E.getMessage());
                }
            }
        });

        btnFind.setBounds(32, 96, 85, 21);
        panel_1.add(btnFind);

        JRadioButton sortById = new JRadioButton("Sắp xếp theo ID");
        sortById.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                empList.sortById();
                empList.renderToTable(tblModel);

            }
        });
        sortById.setBounds(16, 136, 127, 21);
        panel_1.add(sortById);

        JRadioButton sortByName = new JRadioButton("Sắp xếp theo tên");
        sortByName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                empList.sortByName();
                empList.renderToTable(tblModel);
            }
        });
        sortByName.setBounds(16, 159, 127, 21);
        panel_1.add(sortByName);

        ButtonGroup bg = new ButtonGroup();
        bg.add(sortById);
        bg.add(sortByName);

        JButton btnFirst = new JButton("|<");
        btnFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                empList.first();
                lblStatus.setText(empList.getEmployeeInfo());
                isEditMode = true;
                txtName.setText(empList.getCurrenEmployee().getName());
                txtAge.setText("" + empList.getCurrenEmployee().getAge());
                txtEmail.setText(empList.getCurrenEmployee().getEmail());
                txtID.setText(empList.getCurrenEmployee().getId());
                txtSalary.setText("" + empList.getCurrenEmployee().getSalary());
            }
        });
        btnFirst.setBounds(20, 22, 56, 21);
        panel_2.add(btnFirst);

        JButton btnPrevious = new JButton("<<");
        btnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                empList.previous();

                lblStatus.setText(empList.getEmployeeInfo());
                isEditMode = true;
                txtName.setText(empList.getCurrenEmployee().getName());
                txtAge.setText("" + empList.getCurrenEmployee().getAge());
                txtEmail.setText(empList.getCurrenEmployee().getEmail());
                txtID.setText(empList.getCurrenEmployee().getId());
                txtSalary.setText("" + empList.getCurrenEmployee().getSalary());
            }
        });
        btnPrevious.setBounds(86, 22, 56, 21);
        panel_2.add(btnPrevious);

        JButton btnNext = new JButton(">>");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                empList.next();
                lblStatus.setText(empList.getEmployeeInfo());
                isEditMode = true;
                txtName.setText(empList.getCurrenEmployee().getName());
                txtAge.setText("" + empList.getCurrenEmployee().getAge());
                txtEmail.setText(empList.getCurrenEmployee().getEmail());
                txtID.setText(empList.getCurrenEmployee().getId());
                txtSalary.setText("" + empList.getCurrenEmployee().getSalary());
            }
        });
        btnNext.setBounds(152, 22, 56, 21);
        panel_2.add(btnNext);

        JButton btnLast = new JButton(">|");
        btnLast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                empList.last();
                lblStatus.setText(empList.getEmployeeInfo());
                isEditMode = true;
                txtName.setText(empList.getCurrenEmployee().getName());
                txtAge.setText("" + empList.getCurrenEmployee().getAge());
                txtEmail.setText(empList.getCurrenEmployee().getEmail());
                txtID.setText(empList.getCurrenEmployee().getId());
                txtSalary.setText("" + empList.getCurrenEmployee().getSalary());
            }

        });

        btnLast.setBounds(218, 22, 56, 21);
        panel_2.add(btnLast);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 268, 569, 211);
        panel.add(scrollPane);


        tblEmployees = new JTable();
        tblEmployees.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblEmployees.getSelectedRow();

                if (row >= 0) {
                    String empId = (String) tblEmployees.getValueAt(row, 0);
                    employee emp = empList.findById(empId);
                    if (emp != null) {
                        lblStatus.setText(empList.getEmployeeInfo(emp));
                        printInfoEmp(emp);
                    }
                }
            }
        });
        tblEmployees.setFont(new Font("Tahoma", Font.PLAIN, 12));
        tblEmployees.setDefaultEditor(Object.class, null);
        scrollPane.setViewportView(tblEmployees);


        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                int resp = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",
                        "Exit?", JOptionPane.YES_NO_OPTION);

                if (resp == JOptionPane.YES_OPTION) {
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    System.exit(0);
                } else {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    public void printInfoEmp(employee emp1) {
        if (emp1 != null) {
            isEditMode = true;
            txtID.setText(emp1.getId());
            txtName.setText(emp1.getName());
            txtAge.setText("" + emp1.getAge());
            txtEmail.setText(emp1.getEmail());
            txtSalary.setText("" + emp1.getSalary());
        }
    }



}
