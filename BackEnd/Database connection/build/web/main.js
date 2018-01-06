"use strict"

// DOM elements
const hamMenu = document.querySelector('.fa-bars');
const sidebar = document.querySelector('.sidebar');
const file = document.querySelectorAll('.file');
const folder = document.querySelectorAll('.folder');
const lightbox = document.querySelector('.lightbox');
const user_name = document.querySelector('.username');
const uploadFile = document.querySelector('#file');
const uploadLabel = document.querySelector('.upload-span');
const passSign = document.querySelector('#passwordSignUp');
const passLogin = document.querySelector('#passwordLogin');
const home = document.querySelector('#home');
const favorites = document.querySelector('#favorites');
const fileSection = document.querySelector('.files');
const downloadBtn = document.querySelector('.download');
const contentTitle = document.querySelector('.contenttitle');
const deleteForm = document.querySelector('.deleteForm');
let deleteInput = document.querySelector('.deleteInput');


// create new DOM elements
const dropDiv = document.createElement('div'); // container div element
dropDiv.className = 'dropdown';

const downloadLink = document.createElement('a'); // download button
const downloadText = document.createTextNode('Download');
downloadLink.appendChild(downloadText);
downloadLink.className += 'smallButton download';

const favLink = document.createElement('a'); // favorite button
const favText = document.createTextNode('Favourite');
favLink.appendChild(favText);
favLink.className += 'smallButton favorite';

const deleteLink = document.createElement('a'); // delete button
const deleteText = document.createTextNode('Delete');
deleteLink.appendChild(deleteText);
deleteLink.className += 'smallButton delete';

// function that gets cookie from client by cookie name
const getCookie = (name) => {
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1) {
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    }
    else
    {
        begin += 2;
        var end = document.cookie.indexOf(";", begin);
        if (end == -1) {
        end = dc.length;
        }
    }
    // because unescape has been deprecated, replaced with decodeURI
    //return unescape(dc.substring(begin + prefix.length, end));
    return decodeURI(dc.substring(begin + prefix.length, end));
};

// checks user authentication by cookie value
const cookie = () => {
  let checkAuth = getCookie("auth");

  if (checkAuth !== null) {
    let username = getCookie("auth");
    user_name.innerHTML = 'Hello ' + username;
  } else {
      alert("Session expired, please re-login");
      window.location.replace("index.html");
  }
};

document.onload = cookie(); // when user enters home.html

// sets cookie value to null and redirects to login site
const logout = () => {
    let checkAuth = getCookie("auth");
    checkAuth = null;
};

// click listener for hamburger menu button
hamMenu.addEventListener('click', (evt) => {

  sidebar.classList.toggle('slideInLeft');
  sidebar.classList.toggle('hidden');
});

// click listener for various things
document.addEventListener('click', (evt) => {
    let click = evt.target; // target that is clicked

    // checks if you clicked on a info bubble
    // and creates a little dropdown menu
    // and also changes the icon to correspond the right one
    if (click.className.includes('fa-info-circle')) { // the info button of file/folder

      click.className = 'fa fa-2x fa-times-circle';
      // here we construct the dropdown menu from components above
      dropDiv.appendChild(downloadLink);
      dropDiv.appendChild(favLink);
      dropDiv.appendChild(deleteLink);
      click.appendChild(dropDiv);
    } else if (click.className.includes('fa-times-circle')) { // the x button of file/folder
      click.className = 'fa fa-2x fa-info-circle';
      dropDiv.remove();

    } else if (click.className.includes('upload-button')) { // the upload button
      lightbox.classList.toggle('hidden'); // lightbox toggle

    } else if (click.className.includes('upload-x')) { // x button of upload lightbox
      lightbox.classList.toggle('hidden'); // lightbox toggle

    } else if (click.className.includes('download')) { // this is so we can download right files from server
      let notparent = click.parentElement;
      let parent = notparent.parentElement;
      let url = parent.getAttribute('data-fileurl');
      click.setAttribute('href', 'http://' + url);
      click.setAttribute('download', '');

    } else if (click.className.includes('delete')) { // this is so we can delete the right file from server and db
      let notparent = click.parentElement; // dropdown element
      let parent = notparent.parentElement; // i element
      let url = parent.getAttribute('data-filepath'); // this is actually filename
      
      deleteInput.value = url;
      console.log(url);
      console.log(deleteForm.value);
      deleteFile(deleteForm);
    }
});

// function that changes the text on upload form
const changeUploadText = () => {
  if (uploadFile && uploadFile.value) {
    uploadLabel.innerHTML = 'File fetched';
  } else {
    uploadLabel.innerHTML = 'Select a file';

  }
};

// click listener for home menu button
home.addEventListener('click', (evt) => {
  evt.preventDefault();
  getFiles();
});

const getFiles = () => { //here we fetch our own files (VERY IMPORTANTE!!!)

  fetch('db/service/jsonboii?', { // put here the correct servlet
      method: 'GET',
      credentials: "same-origin"
})
  .then( (response) => {
    return response.json();
  })
  .then( (result) => {
    for (let i = 0; i < result.length; i++) { // first get all info in array form (each tuple has array inside that has filename & filepath)

      console.log(result[i]);
      let fileinfo = result[i]; // here we get to do some shit with the individual tuples, first one is always filepath, the second is filename
      // fileinfo[0] == www-filepath
      // fileinfo[1] == filename

      const fileBox = `<div class="file">
      <p class="filedesc">${fileinfo[1]}</p>
      <img src="http://${fileinfo[0]}" alt="${fileinfo[1]}" />
        <i data-fileurl="${fileinfo[0]}" data-filepath="${fileinfo[1]}" class="fa fa-2x fa-info-circle" aria-hidden="true"></i>
      </div>`;

      fileSection.innerHTML += fileBox;

      contentTitle.innerHTML = `<h1>My Files</h1>`;
    }

  });
};

document.onload = getFiles(); // this is so we get our own files when we log in

const deleteFile = (element) => {
  let fData = new FormData(element);

  const settings = {
    credentials: "same-origin",
    method: 'post',
    body: element,
  };
  console.log('fdata: ' + fData);

  fetch('DeleteFile', settings) // deletefile is the name of servlet
  .then( (response) => {
    return response;
  })
  .then( (result) => {
    console.log(result);
  });
  
  deleteForm.submit();
};
