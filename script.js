let payrollData = []

function generatePayroll() {

    let id = document.getElementById("empId").value
    let name = document.getElementById("empName").value
    let basic = parseFloat(document.getElementById("basicSalary").value) || 0
    let allowance = parseFloat(document.getElementById("allowance").value) || 0
    let type = document.getElementById("empType").value
    let bonus = parseFloat(document.getElementById("bonus").value) || 0
    let overtime = parseFloat(document.getElementById("overtime").value) || 0
    let tax = parseFloat(document.getElementById("tax").value) || 0
    let deduction = parseFloat(document.getElementById("deduction").value) || 0

    let gross

    if (type === "Manager") {
        gross = basic + allowance + bonus
    } else {
        gross = basic + allowance + overtime
    }

    let taxAmount = gross * (tax / 100)
    let net = gross - taxAmount - deduction

    let slip = `
==============================
        SALARY SLIP
==============================

Employee ID : ${id}
Employee Name : ${name}
Employee Type : ${type}

Basic Salary : ₹${basic}
Allowance : ₹${allowance}

Gross Salary : ₹${gross}

Tax : ₹${taxAmount}
Other Deduction : ₹${deduction}

------------------------------
Net Salary : ₹${net}
`

    document.getElementById("output").textContent = slip

    payrollData.push({
        id, name, type, basic, allowance, tax, deduction, net
    })

    updateTable()

}



function updateTable() {

    let table = document.querySelector("#payrollTable tbody")

    table.innerHTML = ""

    payrollData.forEach(emp => {

        let row = table.insertRow()

        row.insertCell(0).innerText = emp.id
        row.insertCell(1).innerText = emp.name
        row.insertCell(2).innerText = emp.type
        row.insertCell(3).innerText = emp.basic
        row.insertCell(4).innerText = emp.allowance
        row.insertCell(5).innerText = emp.tax
        row.insertCell(6).innerText = emp.deduction
        row.insertCell(7).innerText = emp.net

    })

}



function printSlip() {
    window.print()
}



function clearFields() {

    document.querySelectorAll("input").forEach(i => i.value = "")

    document.getElementById("output").textContent = ""

}



function exportExcel() {

    let csv = "ID,Name,Type,Basic,Allowance,Tax,Deduction,Net Salary\n"

    payrollData.forEach(emp => {
        csv += `${emp.id},${emp.name},${emp.type},${emp.basic},${emp.allowance},${emp.tax},${emp.deduction},${emp.net}\n`
    })

    let blob = new Blob([csv], { type: "text/csv" })
    let url = URL.createObjectURL(blob)

    let a = document.createElement("a")
    a.href = url
    a.download = "payroll.csv"
    a.click()

}



function showDashboard() {

    let total = 0

    payrollData.forEach(emp => {
        total += emp.net
    })

    alert("Total Payroll Cost : ₹" + total)

}