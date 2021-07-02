

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.table.DefaultTableModel;

public class employeeList {
    private ArrayList<employee> list = new ArrayList<>();
    private int curentIndex = 0;


    public String getEmployeeInfo() {
        return "record "+(curentIndex+1)+" of "+list.size();
    }
    public String getEmployeeInfo(employee emp) {
        curentIndex = list.indexOf(emp);
        return "record "+(curentIndex+1)+" of "+list.size();
    }

    public void first() {
        if(curentIndex > 0) curentIndex = 0;
    }
    public void last() {
        if(curentIndex >= 0) curentIndex = list.size()-1;
    }
    public void next() {
        if(curentIndex < list.size()-1) curentIndex++;
    }
    public void previous() {
        if(curentIndex > 0) curentIndex--;
    }
    public int getCurrenEmployeeIndex() {

        return curentIndex;
    }
    public employee getCurrenEmployee() {
        if(list.size()==0)
            return null;
        return list.get(curentIndex);
    }
    public void add(employee emp) {
        list.add(emp);
    }
    public boolean update(employee emp) {
        employee existedEmp = findById(emp.getId());
        if (existedEmp != null) {
            existedEmp.setAge(emp.getAge());
            existedEmp.setEmail(emp.getEmail());
            existedEmp.setName(emp.getName());
            existedEmp.setSalary(emp.getSalary());
            return true;
        }
        return false;
    }
    //hien len tren bang
    public void renderToTable(DefaultTableModel tblModel) {
        tblModel.setRowCount(0);

        for(employee emp : list) {
            Object[] row = new Object[] {
                    emp.getId(), emp.getName(),
                    emp.getAge(), emp.getEmail(), emp.getSalary()
            };
            tblModel.addRow(row);
        }
        tblModel.fireTableDataChanged();
    }

    public employee findByName(String Name) {
        for (employee emp : list) {
            if (emp.getName().equals(Name)) {
                return emp;
            }

        }
        return null;
    }
    public employee findById(String employeeId) {
        for (employee emp : list) {
            if (emp.getId().equals(employeeId)) {
                return emp;
            }

        }
        return null;
    }
    public boolean deleteById(String employeeId) {
        for (employee emp : list) {
            if (emp.getId().equals(employeeId)) {
                list.remove(emp);
                return true;
            }
        }
        return false;
    }
    public void sortById() {
        Collections.sort(list, new Comparator<employee>() {


            @Override
            public int compare(employee o1, employee o2) {
                // TODO Auto-generated method stub
                return o1.getId().compareTo(o2.getId());
            }
        });
    }
    public void sortByName() {
        Collections.sort(list, new Comparator<employee>() {


            @Override
            public int compare(employee o1, employee o2) {
                // TODO Auto-generated method stub
                return o1.getName().compareTo(o2.getName());
            }
        });
    }


}
