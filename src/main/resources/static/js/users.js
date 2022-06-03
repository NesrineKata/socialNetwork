// fetch all users from REST API and set HTML content
const allUsersUrl = "/rest/users/logged-in";
const activeUsersContainer = document.getElementById('active-users-list');
const activeUsersFragment = document.createDocumentFragment();

console.log('User data retrieved from: ' + allUsersUrl);

function createElementFromHTML(htmlString) {
    const div = document.createElement('div');
    div.innerHTML = htmlString.trim();

    return div.firstChild;
}

let lastUserID = -123;
async function setAllUsersContent() {
    const response = await fetch(allUsersUrl);
    const allUsersData = await response.json();

    if(lastUserID != allUsersData.length-1) {
        activeUsersContainer.innerHTML = '';

        for (let i = 0; i < allUsersData.length; i++) {
            const li = createElementFromHTML(
                '<a class="row mt-1 btn btn-light btn-lg btn-block btn-active-user" href="/profile/' + allUsersData[i] +'"><img src="/img/user.svg" width="20px" height="20px"> '+ allUsersData[i] + '</a>'
            );

            activeUsersFragment.appendChild(li);
            activeUsersContainer.appendChild(activeUsersFragment);
        }

        lastUserID = allUsersData.length - 1;
    }
}

setAllUsersContent();
setInterval(setAllUsersContent, 5000);