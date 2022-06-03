// fetch all users from REST API and set HTML content
const imageContainer = document.getElementById('image');
const imageFragment = document.createDocumentFragment();

const dateContainer = document.getElementById('date');
const dateFragment = document.createDocumentFragment();

let tmp_url = "/rest/posts/id/";
let href_words = window.location.href.split("/");
post_url = tmp_url.concat(href_words[4]);

console.log('Post data retrieved from: ' + post_url);

async function setImage() {
    const response = await fetch(post_url);
    const postData = await response.json();

    const li = createElementFromHTML(
        '<img src="data:image/jpg;base64,'+ postData.image +'"class="row ml-auto mr-auto w-100 mw-100" alt=" ">'
    );

    imageFragment.appendChild(li);
    imageContainer.appendChild(imageFragment);
}


async function setDate() {
    const response = await fetch(post_url);
    const postData = await response.json();

    let date = new Date(postData.unixTime * 1000),
        dateValues = [
            date.getFullYear(),
            date.getMonth()+1,
            date.getDate(),
            date.getHours(),
            date.getMinutes(),
        ];
    let yearString = dateValues[0] + '-' + dateValues[1] + '-' + dateValues[2];
    let timeString = dateValues[3] + ':' + dateValues[4];

    const li = createElementFromHTML(
        '<small>' + yearString + ', ' + timeString + '</small>'
    );

    dateFragment.appendChild(li);
    dateContainer.appendChild(dateFragment);
}

setImage();
setDate();