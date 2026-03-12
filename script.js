function calculate() {

    let id = document.getElementById("id").value
    let name = document.getElementById("name").value
    let basic = parseFloat(document.getElementById("basic").value)
    let allowance = parseFloat(document.getElementById("allowance").value)
    let tax = parseFloat(document.getElementById("tax").value)
    let deduction = parseFloat(document.getElementById("deduction").value)

    let gross = basic + allowance
    let taxAmount = gross * (tax / 100)
    let net = gross - taxAmount - deduction

    let result = `
SALARY SLIP
--------------------------
Employee ID : ${id}
Employee Name : ${name}

Basic Salary : ${basic}
Allowance : ${allowance}

Gross Salary : ${gross}

Tax : ${taxAmount}
Deduction : ${deduction}

Net Salary : ${net}
`

    document.getElementById("output").textContent = result
}