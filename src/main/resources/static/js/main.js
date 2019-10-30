var IMG_INDEX = 0;

var base_url = window.location.origin;


var IMG_INDEX_LIST;
var token = document.getElementById("newImage").getAttribute("token");

function uploadImg() {

    console.log("token: " + token);

    var file = document.getElementById("newImage").files[0];
    var fileName = file.name;
    var fileExt = fileName.split('.').pop().toLowerCase();
    if (jQuery.inArray(fileExt, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
        alert("Invalid image file");
    }

    var imageSize = file.size;
    if (imageSize > 8000000) {
        alert("Image file size is too big");
    } else {

        var data = new FormData();
        data.append("file", file);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: base_url + "/upload/images?token=" + token,
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("SUCCESS : ", data);
                printImage(data);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });

    }

}

function printImage(data) {
    var imageDiv = document.getElementById("images");

    var div = document.createElement("divA");
    div.id = "diva" + data.id;
    div.setAttribute("class", "imgContainer");

    var img1 = document.createElement("img");
    img1.id = "image" + data.id;
    img1.src = "data:" + data.type + ";base64," + data.image;
    img1.setAttribute("class", "img-thumbnail");

    var x = document.createElement("a");
    x.id = "image" + data.id;
    x.setAttribute("class", "x-button btn btn-primary");
    x.setAttribute("onclick", "deleteImg(" + data.id + ")");

    var i = document.createElement("i");
    i.setAttribute("class", "far fa-trash-alt");

    x.appendChild(i);

    div.appendChild(img1);
    div.appendChild(x);

    // <i class="far fa-trash-alt"></i>

    var secondChild = imageDiv.getElementsByTagName('imgA')[0];

    if (secondChild != null) {
        imageDiv.insertBefore(div, secondChild);


    } else {
        imageDiv.appendChild(div);

    }

}


