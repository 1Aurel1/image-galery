var base_url = window.location.origin;


$(document).ready(function () {

    $('.simple-ajax-popup-align-top').magnificPopup({
        type: 'ajax',
        midClick: true,
        mainClass: 'mfp-fade',
        closeOnBgClick: false,
        overflowY: 'scroll' // as we know that popup content is tall we set scroll overflow by default to avoid jump
    });


});

function deleteAlbum(id) {

    if (confirm("Are you sure ?")) {

        var url = base_url + "/api/albums" + "/" + id;
        var hr = new XMLHttpRequest();
        hr.open("DELETE", url, true);
        hr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        console.log(hr);

        hr.onreadystatechange = function () {

            if (hr.readyState == 4 && hr.status == "204") {

                $('*[data-delete-album="' + id + '"]').parent().parent().remove();

            }
        }

        hr.send();
    }

}
