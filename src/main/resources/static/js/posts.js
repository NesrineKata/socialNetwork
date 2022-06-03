// fetch all users from REST API and set HTML content
const postsContainer = document.getElementById('posts-container');
const postsFragment = document.createDocumentFragment();

console.log('User data retrieved from: ' + POSTS_URL);

let lastPostID = -123;
async function setAllUsersContent() {
    const response = await fetch(POSTS_URL);
    const allPostsData = await response.json();

    if(lastPostID != allPostsData.length-1) {
        postsContainer.innerHTML = '';

        for (let i = 0; i < allPostsData.length; i++) {
            let date = new Date(allPostsData[i].unixTime * 1000),
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
                '                <a href="/post/'+ allPostsData[i].id +'" class="mt-2 post-item mb-3 list-group-item list-group-item-action flex-column align-items-start">\n' +
                '                    <div class="d-flex w-100 justify-content-between">\n' +
                '                        <h5 class="mb-1">'+ allPostsData[i].username +'</h5>\n' +
                '                        <small>'+yearString +', '+ timeString +'</small>\n' +
                '                    </div>\n' +
                '                    <p class="mb-1">'+ allPostsData[i].content +'</p>\n' +
                '                    <img src="data:image/jpg;base64,' + allPostsData[i].image + '" class="row ml-auto mr-auto w-75 mw-100" alt=" ">\n' +
                '                    <small>'+ allPostsData[i].id +'</small>\n' +
                '                </a>'
            );

            postsFragment.appendChild(li);
            postsContainer.appendChild(postsFragment);
        }

        lastPostID = allPostsData.length - 1;
    }
}

setAllUsersContent();
setInterval(setAllUsersContent, 5000);