package com.chetverik.excelpoi;

import com.chetverik.domain.contract.Contract;
import com.chetverik.domain.contract.ContractFieldNames;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ExcelImportUtil {
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private List<List<Object>> contractList;
    private List<List<String>> purchaseList;

    public ExcelImportUtil(String pathFile) {
        contractList = new LinkedList<List<Object>>();
        purchaseList = new LinkedList<>();

        if (!pathFile.isEmpty() && !pathFile.isBlank()) {
            HSSFWorkbook workbook = readWorkbook(pathFile);
            readContracts(workbook);
        }
    }

    private HSSFWorkbook readWorkbook(String filename) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filename));
            this.wb = new HSSFWorkbook(fs);
            return wb;
        } catch (Exception e) {
            return null;
        }
    }

    private void readContracts(HSSFWorkbook wb) {
        HSSFSheet contractsSheet = wb.getSheet("Contracts");
        for (int i = 2; i < (contractsSheet.getLastRowNum() + 1); i++) {
            HSSFRow row = contractsSheet.getRow(i);
            if (row.getCell(i) != null) {
                List<Object> rowString = new LinkedList<>();
                for (int j = 0; j < ContractFieldNames.getContractNames().length; j++) {
                    if (row.getCell(j).getCellType() == CellType.STRING) {
                        rowString.add(row.getCell(j).getStringCellValue());
                    } else if (row.getCell(j).getCellType() == CellType.NUMERIC) {
                        rowString.add(row.getCell(j).getNumericCellValue());
                    }
                }
                contractList.add(rowString);
            }
        }
    }

    public List<Contract> getContractListString() {
        return ExcelRowsParser.Companion.getContractList(contractList);
    }

    public List<List<String>> getPurchaseListString() {
        return purchaseList;
    }
}
