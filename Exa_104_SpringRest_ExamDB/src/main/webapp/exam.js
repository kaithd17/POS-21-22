const createElement = (tag, text, attributes = {}) => {
    const element = document.createElement(tag);
    element.innerText = text;
    for (const attribute in attributes) {
        element.setAttribute(attribute, attributes[attribute]);
    }
    return element;
}

window.onload = async () => {
    const response = await fetch("/classname", {method: 'GET'});
    const classnames = await response.json();

    let selectBox = document.getElementsByClassName("classNameSelectBox")[0];
    for (let className of classnames) {
        selectBox.appendChild(createElement('option', className.classname, {value: className.classname}));
    }

    selectBox.addEventListener('change', (e) => {
        getStudents(0, e.target.value);
    })
}



const getStudents = async (page, classname) => {
    console.log(page + "       " + classname);
    const response = await fetch(`/student/getStudents?page=${page}&classname=${classname}`);
    const students = await response.json();
    console.log(students["content"][0].studentId);

    let table = document.getElementById("studentTable");
    for(student of students["content"]) {
        let tr = document.createElement('tr');
        tr.appendChild(createElement('td', student.studentId));
        tr.appendChild(createElement('td', student.firstname));
        tr.appendChild(createElement('td', student.lastname));
        tr.appendChild(createElement('button', 'Show Exams'));
        table.appendChild(tr);
    }
}