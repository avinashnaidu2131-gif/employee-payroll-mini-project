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
}

function printSlip() {
    window.print()
}

function clearFields() {
    document.querySelectorAll("input").forEach(input => input.value = "")
    document.getElementById("output").textContent = ""
}

function exitPage() {
    alert("Close the browser tab to exit.")
}

function exportExcel() {
    alert("Excel export feature demo.")
}

function showDashboard() {
    alert("Dashboard feature coming soon.")
}