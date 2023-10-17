package com.chetverik.excelpoi

import com.chetverik.domain.contract.Contract
import com.chetverik.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Objects

@Service
class ExcelRowsParser() {
    companion object {

        @Autowired
        private val contractList = arrayListOf<Contract>()

        @Autowired
        private val branchRepo: BranchRepo? = null

        @Autowired
        private val typePurchaseRepo: TypePurchaseRepo? = null

        @Autowired
        private val typeCompanyRepo: TypeCompanyRepo? = null

        @Autowired
        private val supplierRepo: SupplierRepo? = null

        @Autowired
        private val userRepo: UserRepo? = null


        fun getContractList(list: List<List<String>>): ArrayList<Contract> {
            for (item in list) {
                println(item)
                contractList.add(
                    Contract(
                        branchRepo?.findByName(item[0]),
                        item[1],
                        item[2],
                        typePurchaseRepo?.findByNameTypeOfPurchase(item[3]),
                        item[4],
                        item[5],
                        if (item[6] != null){
                            item[6].toDouble()
                        } else 0.0,
                        item[7],
                        supplierRepo?.findBynameSupplier(item[8]),
                        typeCompanyRepo?.findBynameTypeCompany(item[9]),
                        item[10],
                        item[11],
                        item[12],
                        item[13],
                        item[15],
                        item[16],
                        userRepo?.findByUsername(item[17])
                    )
                )
            }
            return contractList
        }
    }
}