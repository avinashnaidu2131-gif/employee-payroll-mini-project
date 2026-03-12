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

    let table = document.getElementById("payrollTable").getElementsByTagName("tbody")[0]

    let row = table.insertRow()

    row.insertCell(0).innerHTML = id
    row.insertCell(1).innerHTML = name
    row.insertCell(2).innerHTML = type
    row.insertCell(3).innerHTML = basic
    row.insertCell(4).innerHTML = allowance
    row.insertCell(5).innerHTML = tax
    row.insertCell(6).innerHTML = deduction
    row.insertCell(7).innerHTML = net
}



function printSlip() {
    window.print()
}



function clearFields() {

    document.querySelectorAll("input").forEach(i => i.value = "")
    document.getElementById("output").textContent = ""

}



function exportExcel() {

    let table = document.getElementById("payrollTable")
    let csv = []

    for (let i = 0; i < table.rows.length; i++) {

        let row = [], cols = table.rows[i].querySelectorAll("td,th")

        for (let j = 0; j < cols.length; j++) {
            row.push(cols[j].innerText)
        }

        csv.push(row.join(","))
    }

    let csvFile = new Blob([csv.join("\n")], { type: "text/csv" })
    let downloadLink = document.createElement("a")

    downloadLink.download = "payroll.csv"
    downloadLink.href = window.URL.createObjectURL(csvFile)

    downloadLink.click()

}



function showDashboard() {

    let rows = document.querySelectorAll("#payrollTable tbody tr")

    let total = 0

    rows.forEach(r => {
        total += parseFloat(r.cells[7].innerText)
    })

    alert("Total Payroll Cost : ₹" + total)

}