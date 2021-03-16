// Creating custom context menu.
const menuArea = document.querySelectorAll('.element');
const menu = document.querySelector('#right-click-menu');
for (let i = 0; i < menuArea.length; i++) {
    menuArea[i].addEventListener('contextmenu', event => {
        event.preventDefault();
        menu.style.top = `${event.clientY}px`;
        menu.style.left = `${event.clientX}px`;
        menu.classList.add('active');
    }, false)
}

document.addEventListener('click', event => {
    if (event.button !== 2) {
        menu.classList.remove('active');
    }
}, false);

menu.addEventListener('click', event => {
    event.stopPropagation();
}, false);

// Main program logic.
let jwtToken = undefined;
let username = 'guest';
let role = 'guest';

let serviceEndpoint = 'http://localhost:8081';
let loginEndpoint = '/login';
let createFileEndpoint = '/create-file';
let deleteFileEndpoint = '/delete-file';

let currentPath = '/home/mishamba';
let currentFiles = {};

const signInButton = document.querySelector('.sign-in-button');
signInButton.addEventListener('click', signInProcessor());

async function signInProcessor() {
    let username = document.querySelector('#username-input');
    let password = document.querySelector('#password-input');

    let response = await fetch(serviceEndpoint + loginEndpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: username, password: password })
    });

    let jsonResponse = await response.json();

    jwtToken = jsonResponse['jwt'];
    username = jsonResponse['username'];
    role = jsonResponse['role'];

    signInButton.classList.remove('active');
}

let deleteFileElement = document.createAttribute('li');
deleteFileElement.className = 'delete-file';
deleteFileElement.innerHTML = 'Delete file';

function configurePageFunctions() {
    let deleteFileOption = document.querySelector('#delete-file');
    let createFileButton = document.querySelector('.create-file-button');
    let activeCreateFileButton = document.querySelector('.create-file-button.active');

    if (role === 'ADMIN') {
        // Appending delete file option to context menu.
        if (deleteFileOption === null) {
            menu.append(deleteFileElement);
            deleteFileElement.addEventListener('click', deleteFile());
        }

        // Making create file button visible.
        if (createFileButton === null) {
            createFileButton.classList.add('active');
            activeCreateFileButton = createFileButton;
        }
    } else {
        // Removing delete file option from context menu.
        if (deleteFileOption !== null) {
            menu.remove(deleteFileElement);
        }

        // Making create file button non-visible.
        if (activeCreateFileButton !== null) {
            activeCreateFileButton.classList.remove('active');
            createFileButton = activeCreateFileButton;
        }
    }
}

async function deleteFile() {
    let fileName = findFileName();

    let response = await fetch(serviceEndpoint + deleteFileEndpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ fileName: fileName, path: currentPath })
    });

    if (response.ok) {
        alert('Deleted file!');
        openForlderByPath(currentPath);
    } else {
        alert('Can\'t delete file');
    }
}

function openForlder() {
    openForlderByPath(currentPath + findFileName());
}

function findFileName() {
    let box = menu.getBoundingClientRect();
    let element = elementFromPoint(box.top + clientY, box.left + clientX);
    return element.children[1].textContent;
}

async function openFolderByPath(path) {
    let response = await fetch(serviceEndpoint + path)

    if (response.ok) {
        let jsonResponse = await response.json();

        for (let i = 0; i < jsonResponse.length; i++) {
            currentFiles.put({ fileName: jsonResponse[i]['name'], type: jsonResponse[i]['type'] });
        }
    } else {
        alert('can\'t open folder.');
    }
}

function showFiles() {
    let table = document.querySelector('.table');

    removeAllChild(table);

    let lines = {};

    for (let i = 0; i < currentFiles.length; i++) {
        let line = document.createAttribute('tr');

        // This sub cycle makes each line 9 elements length.
        for (; i % 9 <= 9; i++) {
            line.appendChild(formTagFromFile(currentFiles[i]));
        }

        lines.put(line);
    }

    lines.forEach(element => {
        table.appendChild(element);
    })
}

// TODO
// add customize menu for each folder and file.

function removeAllChild(tag) {
    while (tag.firstChild) {
        tag.removeChild(tag.firstChild);
    }
}

function formTagFromFile(file) {
    let element = document.createAttribute('button');
    let elementImage = document.createAttribute('img');
    let elementName = document.createAttribute('p');

    if (currentFiles[i].type === 'folder') {
        elementImage.setAttribute('src', 'folder.png');
    } else {
        elementImage.setAttribute('src', 'file.png');
    }

    elementName.textContent = currentFiles[i].fileName;
    elementName.classList.add("element");

    element.appendChild(elementImage);
    element.appendChild(elementName);

    return element;
}

/*
JSON FORMAT

{
    {
        'name': 'smth.txt',
        'type': 'file'
    },
    {
        'name': 'another.txt',
        'type': 'file'
    },
    {
        'name': 'asdf',
        'type': 'folder'
    }
}
*/

async function createFile() {
    let fileName = prompt('Enter file name', 'smth.txt');

    let response = await fetch(serviceEndpoint + createFileEndpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ fileName: fileName, path: currentPath })
    });

    if (response.ok) {
        alert('Created file!');
        openForlderByPath(currentPath);
    } else {
        alert('can\'t create file');
    }
}