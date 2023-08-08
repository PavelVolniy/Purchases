package com.chetverik.excelpoi;

import com.chetverik.domain.contract.Contract;
import com.chetverik.domain.contract.ContractFieldNames;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelExportUtil {
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private List<Contract> contractList;

    public ExcelExportUtil(List<Contract> contractList) {
        this.contractList = contractList;
        wb = new HSSFWorkbook();
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        cell.setCellStyle(style);
        sheet.autoSizeColumn(columnCount);
    }

    private void createHeaderRow(String nameSheet, Short heightFont, int countColumns) {
        sheet = wb.createSheet(nameSheet);
        Row row = sheet.createRow(0);
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBold(true);
        font.setFontHeight(heightFont);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        createCell(row, 0, nameSheet, style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,countColumns));
        font.setFontHeightInPoints((short) 10);

        row = sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight((short) 16);
        style.setFont(font);
        String[] names = ContractFieldNames.getContractNames();
        for (int i = 0; i < names.length; i++) {
            createCell(row, i, names[i], style);
        }
    }

    private void writeContractData(short heightFont){
        int rowCount = 2;
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontHeight(heightFont);
        style.setFont(font);

        for (Contract contract :contractList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, contract.getBranch().getName(), style);
            createCell(row, columnCount++, contract.getNameOfContract(), style);
            createCell(row, columnCount++, contract.getPp_poz_ep(), style);
            createCell(row, columnCount++, contract.getTypeOfPurchase().getNameTypeOfPurchase(), style);
            createCell(row, columnCount++, contract.getNumberOfContract(), style);
            createCell(row, columnCount++, contract.getDateOfContract(), style);
            createCell(row, columnCount++, contract.getSum(), style);
            createCell(row, columnCount++, contract.getDateOfExecutionContract(), style);
            createCell(row, columnCount++, contract.getSupplier().getNameSupplier(), style);
            createCell(row, columnCount++, contract.getSupplier().getInn(), style);
            createCell(row, columnCount++, contract.getTypeOfCompany().getNameTypeCompany(), style);
            createCell(row, columnCount++, contract.getNumberOfRegistryEntry(), style);
            createCell(row, columnCount++, contract.getAdditionalAgreement(), style);
            createCell(row, columnCount++, contract.getOkdp2(), style);
            createCell(row, columnCount++, contract.getF_i_o(), style);
        }

    }

    public void exportDataToExcel(HttpServletResponse response, String nameSheet) throws IOException {
        createHeaderRow(nameSheet, (short) 30, 10);
        writeContractData((short) 20);
        ServletOutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        wb.close();
        outputStream.close();
    }

}
