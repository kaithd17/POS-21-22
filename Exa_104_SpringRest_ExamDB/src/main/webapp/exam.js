const createElement = (tag, text, attributes = {}) => {
    const element = document.createElement(tag);
    element.innerText = text;
    for (const attribute in attributes) {
        element.setAttribute(attribute, attributes[attribute]);
    }
    return element;
}

const setHidden = () => {
    document.getElementById("examForm").hidden = true;
    document.getElementById("examContainer").hidden = true;
    document.getElementById("examChangeForm").hidden = true;
}

const loadClasses = async (classnames) => {
    let selectBox = document.getElementsByClassName("classNameSelectBox")[0];
    for (let className of classnames) {
        selectBox.appendChild(createElement('option', className.classname, {value: className.classname}));
    }

    selectBox.addEventListener('change', (e) => {
        getStudents(0, e.target.value, true);
    });
    setHidden();
}

window.onload = async () => {
    const response = await fetch("/classname", {method: 'GET'});
    const classnames = await response.json();
    await loadClasses(classnames);
}



const getStudents = async (page, classname, newClass) => {
    setHidden();

    const response = await fetch(`/student/getStudents?page=${page}&classname=${classname}`);
    const students = await response.json();

    if (newClass) {
        let classSelector = document.getElementById("classSelector");
        classSelector.innerHTML = "";
        classSelector.innerText = "Page: ";
        let selectBoxPages = document.createElement('select');
        for(let i = 0; i < students["totalPages"]; i++){
            selectBoxPages.appendChild(createElement('option', i+'', {value: i}));
        }
        selectBoxPages.addEventListener('change', (event) => {
            getStudents(event.target.value, classname, false);
        });
        classSelector.appendChild(selectBoxPages);
    }

    let table = document.getElementById("studentTable");
    let examTable = document.getElementById("examTable");
    examTable.innerHTML = "";
    table.innerHTML = "";
    table.appendChild(createElement('th', 'ID'));
    table.appendChild(createElement('th', 'Firstname'));
    table.appendChild(createElement('th', 'Lastname'));
    for (student of students["content"]) {
        let tr = document.createElement('tr');
        tr.appendChild(createElement('td', student.studentId));
        tr.appendChild(createElement('td', student.firstname));
        tr.appendChild(createElement('td', student.lastname));

        let button = createElement('button', 'Show Exams', {value: student.studentId});
        button.addEventListener('click', (event) => {
            getExams(event.target.value);
        });

        let button2 = createElement('button', 'Add Exam', {value: student.studentId});
        button2.addEventListener('click', (event) => {
            showForm(event.target.value);
        });
        tr.appendChild(button2);

        tr.appendChild(button);
        table.appendChild(tr);
    }
}

const getExams = async (studentId) => {
    setHidden();
    document.getElementById("examContainer").hidden = false;

    const response = await fetch(`/exam/getExams?studentId=${studentId}`);
    const exams = await response.json();

    console.log(exams);
    let table = document.getElementById("examTable");
    let studentName = document.getElementById("nameOfStudent");

    if (exams.length != 0) {
        studentName.innerText = "";
        studentName.innerText = "Exams of " + exams[0].student.firstname + " " + exams[0].student.lastname + ": ";

        //Create ExamTable
        table.innerHTML = "";
        table.appendChild(createElement('th', 'ID'));
        table.appendChild(createElement('th', 'Date of Exam'));
        table.appendChild(createElement('th', 'Duration'));
        table.appendChild(createElement('th', 'Subject'));
        for (exam of exams) {
            let tr = document.createElement('tr');
            tr.appendChild(createElement('td', exam.examId));
            tr.appendChild(createElement('td', exam.dateOfExam));
            tr.appendChild(createElement('td', exam.duration));
            tr.appendChild(createElement('td', exam.subject.shortname));

            let button = createElement('button', 'Delete Exam', {value: exam.examId});
            button.addEventListener('click', (event) => {
                deleteExam(event.target.value, studentId);
            });
            tr.appendChild(button);

            let button2 = createElement('button', 'Change Exam', {value: exam.examId});
            button2.addEventListener('click', (event) => {
                setExam(event.target.value);
            });
            tr.appendChild(button2);

            table.appendChild(tr);
        }
    } else {
        studentName.innerText = "";
        table.innerHTML = "";
        const response = await fetch(`/student/getStudent?studentId=${studentId}`);
        const student = await response.json();
        alert(student.firstname + " " + student.lastname + " does not have any current exams!")
    }

}

const deleteExam = async (examId, studentId) => {
    const response = await fetch(`/exam/deleteExam?examId=${examId}`, {method: 'DELETE'});

    if (response.ok) {
        await getExams(studentId);
    } else {
        alert("Exam was not deleted!");
    }
}

const showForm = async (studentId) => {
    setHidden();
    document.getElementById("examForm").hidden = false;

    const response = await fetch(`/subject`);
    const subjects = await response.json();

    const response2 = await fetch(`/student/getStudent?studentId=${studentId}`);
    const student = await response2.json();

    let studentName = document.getElementById("studentName");
    studentName.innerText = "";
    studentName.innerText = "Create exam for " + student.firstname + " " + student.lastname;

    let selectBoxSubject = document.getElementById("selectBoxSubject");
    for (let subject of subjects) {
        selectBoxSubject.appendChild(createElement('option', subject.longname, {value: subject.subjectId}));
    }

    let createExamButton = document.getElementById("createExamButton");
    createExamButton.addEventListener('click', (event) => {
        createExam(studentId);
    })
}

const createExam = async (studentId) => {
    let dateOfExam = document.getElementById("dateOfExam").value;
    let duration = document.getElementById("duration").value;
    let subjectId = document.getElementById("selectBoxSubject").value;

    if (dateOfExam == "" || isNaN(duration)){
        alert("Invalid Form!")
    } else {
        let exam = {
            studentId: studentId,
            dateOfExam: dateOfExam,
            duration: duration,
            subjectId: subjectId,
        }
        console.log(exam);

        const response = await fetch(`/exam/createExam`, {method: 'POST',
            headers: {
                'Content-Type':'application/json'
            },
            body: JSON.stringify(exam)});

        if (response.ok) {
            getExams(studentId);
        }
    }
}

const setExam = async (examId) => {
    setHidden();
    document.getElementById("examChangeForm").hidden = false;

    document.getElementById("changeExamText").innerText = "Change Exam: ";

    const response = await fetch(`/exam/getExam?examId=${examId}`);
    const exam = await response.json();

    const response2 = await fetch(`/subject`);
    const subjects = await response2.json();

    let selectBoxSubject2 = document.getElementById("selectBoxSubject2");
    for (let subject of subjects) {
        selectBoxSubject2.appendChild(createElement('option', subject.longname, {value: subject.subjectId}));
    }

    document.getElementById("dateOfExam2").value = exam.dateOfExam;
    document.getElementById("duration2").value = exam.duration;
    document.getElementById("selectBoxSubject2").selected = exam.subject.longname;

    let button = document.getElementById("changeButton");
    button.addEventListener('click', (event) => {
        changeExam(examId, exam.student.studentId);
    });
}

const changeExam = async (examId, studentId) => {
    let dateOfExam = document.getElementById("dateOfExam2").value;
    let duration = document.getElementById("duration2").value;
    let subjectId = document.getElementById("selectBoxSubject2").value;

    let exam = {
        dateOfExam: dateOfExam,
        duration: duration
    }
    console.log(exam);

    const response = await fetch(`/exam/changeExam?examId=${examId}&subjectId=${subjectId}`, {method: 'PATCH',
        headers: {
            'Content-Type':'application/json'
        },
        body: JSON.stringify(exam)});

    if (response.ok) {
        await getExams(studentId)
    }
}