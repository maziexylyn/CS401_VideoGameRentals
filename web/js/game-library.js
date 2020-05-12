let gameModal = document.getElementById('game-modal');
let gameModalInstance;
document.addEventListener('DOMContentLoaded', function() {
    gameModalInstance = M.Modal.init(gameModal, {});
});

var ratings = [];
var publishers = [];
var genres = [];
var platforms = [];
var gamePlatformInfos = [];

let rentRate = 0.10;

readGamePlatformInfo();
readListGenre();
readListPlatform();
readListPublisher();
readListRating();

function readGamePlatformInfo(){
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../Servlets.GamePlatformInfo.Read", true);
    xhttp.setRequestHeader('Content-Type', "application/x-www-form-urlencoded");
    xhttp.send();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
           //console.log(this.responseText);
            gamePlatformInfos = JSON.parse(this.responseText).data;
            console.log(gamePlatformInfos);
            buildLibrary();

        } else if (this.readyState === 4) {
            // console.log(this.responseText);
            // M.toast({html: JSON.parse(this.responseText).msg})
        }
    };
}

function readListGenre(){
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../Servlets.Genre.ReadList", true);
    xhttp.setRequestHeader('Content-Type', "application/x-www-form-urlencoded");
    xhttp.send("isActive=1");

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //console.log(this.responseText);
            genres = JSON.parse(this.responseText).data;
            console.log(genres);
            buildLibrary();

        } else if (this.readyState === 4) {
            // console.log(this.responseText);
            // M.toast({html: JSON.parse(this.responseText).msg})
        }
    };
}

function readListPlatform(){
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../Servlets.Platform.ReadList", true);
    xhttp.setRequestHeader('Content-Type', "application/x-www-form-urlencoded");
    xhttp.send("isActive=1");

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //console.log(this.responseText);
            platforms = JSON.parse(this.responseText).data;
            console.log(platforms);
            buildLibrary();
        } else if (this.readyState === 4) {
            // console.log(this.responseText);
            // M.toast({html: JSON.parse(this.responseText).msg})
        }
    };
}

function readListPublisher(){
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../Servlets.Publisher.ReadList", true);
    xhttp.setRequestHeader('Content-Type', "application/x-www-form-urlencoded");
    xhttp.send("isActive=1");

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //console.log(this.responseText);
            publishers = JSON.parse(this.responseText).data;
            console.log(publishers);
            buildLibrary();
        } else if (this.readyState === 4) {
            // console.log(this.responseText);
            // M.toast({html: JSON.parse(this.responseText).msg})
        }
    };
}

function readListRating(){
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../Servlets.Rating.ReadList", true);
    xhttp.setRequestHeader('Content-Type', "application/x-www-form-urlencoded");
    xhttp.send("isActive=1");

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            // console.log(this.responseText);
            ratings = JSON.parse(this.responseText).data;
            console.log(ratings);
            buildLibrary();
        } else if (this.readyState === 4) {
            // console.log(this.responseText);
            // M.toast({html: JSON.parse(this.responseText).msg})
        }
    };
}

function checkInfo(){
    const arrays = [ ratings, platforms, genres, publishers, gamePlatformInfos];
    var flag = true;
    for(var ii=0; ii<arrays.length; ii++){
        if(arrays[ii].length === 0){
            flag = false;
            break;
        }
    }
    return flag;
}

function buildLibrary(){

    console.log("checking...");
    if(checkInfo()){
        console.log("TIME TO BUILD LIBRARY!!!");


        const library = document.getElementById("library-root");

        let row = document.createElement("div");

        for(let ii = 0; ii<gamePlatformInfos.length; ii++){
            if(ii%3 === 0){
                row = document.createElement("div");
                row.classList.add("row");
            }
            let column = document.createElement("div");
            column.classList.add("col", "s4");

            column.appendChild(createCard(ii));

            row.appendChild(column);

            if(ii%3 === 2){
                library.appendChild(row);
            }
        }
    }
}

function createCard( index ){
    let card = document.createElement("div");
    card.classList.add("card");

    card.appendChild(createCardImage(index));
    card.appendChild(createCardContent(index));

    return card;
}

function createCardImage(index){
    let cardImage = document.createElement("div");
    cardImage.classList.add("card-image");

    let image = document.createElement("img");
    image.src = gamePlatformInfos[index].imagePath;
    image.alt = gamePlatformInfos[index].title + " cover image";
    image.height = "400";
    image.setAttribute('onclick', 'createModal('+gamePlatformInfos[index].game_id+',"'+ gamePlatformInfos[index].platform_ids+'")');

    let span = document.createElement("span");
    span.classList.add("card-title", "red");

    let bold = document.createElement("b");
    bold.innerText = "$" + (gamePlatformInfos[index].currentPrice * rentRate).toFixed(2);

    let anchor = document.createElement("a");
    anchor.classList.add("btn-floating","btn-large","halfway-fab","waves-effect","waves-light", "red");
    anchor.setAttribute('onclick', "createToast()")

    let icon = document.createElement("i");
    icon.classList.add("material-icons");
    icon.innerText = "add_shopping_cart";

    anchor.appendChild(icon);
    span.appendChild(bold);

    cardImage.appendChild(image);
    cardImage.appendChild(span);
    cardImage.appendChild(anchor);

    return cardImage;
}

function createToast(){
    M.toast({html: "Added to Cart"});
}

function createCardContent(index){
    let cardContent = document.createElement("div");
    cardContent.classList.add("card-content");

    let heading = document.createElement("h5");
    heading.innerText = gamePlatformInfos[index].title;

    let platform_ids = (gamePlatformInfos[index].platform_ids).split(",");

    let genre = createChip();
    genre.innerText = genres[gamePlatformInfos[index].genre_id - 1].name;

    let rating = createChip();
    rating.innerText = "Rated " + ratings[gamePlatformInfos[index].rating_id - 1].name;

    cardContent.appendChild(heading);
    for(let ii=0; ii<platform_ids.length; ii++){
        cardContent.appendChild(createChipWithImage(platform_ids[ii] - 1));
    }

    cardContent.appendChild(genre);
    cardContent.appendChild(rating);

    return cardContent;
}

function createChipWithImage(platform_id){
    let chip = document.createElement("div");
    chip.classList.add("chip");

    let image = document.createElement("img");
    image.src = platforms[platform_id].imagePath;
    image.alt = "";

    chip.appendChild(image);
    chip.innerHTML = chip.innerHTML + platforms[platform_id].name;

    return chip;
}

function createChip(){
    let chip = document.createElement("div");
    chip.classList.add("chip");
    return chip;
}

function createModal(game_id, platforms){
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../Servlets.Game.Read", true);
    xhttp.setRequestHeader('Content-Type', "application/x-www-form-urlencoded");
    xhttp.send("id=" + game_id);

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //console.log(this.responseText);
            let modalContent = JSON.parse(this.responseText).data;
            console.log(modalContent);

            let modalImage = document.getElementById("modal-img");
            modalImage.src = modalContent.imagePath;
            modalImage.alt = modalContent.title + " cover image";
            // modalImage.height = "350";

            document.getElementById("modal-title").innerText = modalContent.title;

            let modalPlatforms = document.getElementById("modal-platforms");
            modalPlatforms.innerText ="";
            let platform_ids = platforms.split(",");
            for(let ii=0; ii<platform_ids.length; ii++){
                modalPlatforms.appendChild(createChipWithImage(platform_ids[ii] - 1));
            }

            document.getElementById("modal-rating").innerText = "Rated " + ratings[modalContent.rating_id - 1].name;
            document.getElementById("modal-genre").innerText = genres[modalContent.genre_id - 1].name;
            document.getElementById("modal-publisher").innerText = publishers[modalContent.publisher_id - 1].name;
            document.getElementById("modal-description").innerText = modalContent.description;

            gameModalInstance.open();

        } else if (this.readyState === 4) {
            // console.log(this.responseText);
            // M.toast({html: JSON.parse(this.responseText).msg})
        }
    };
}




