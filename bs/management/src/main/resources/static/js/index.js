function showLab(url, title, aside) {
    if (url.trim() === "") return;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "html"
    }).done(function (data) {
        if (title.trim() !== "") {
            var tpl = title;
            if (aside.trim() !== "") {
                tpl += "<small>" + aside + "</small>";
            }
            $("#content-header").html(tpl);
        }
        $("#container-view").html(data);
    })
}


function messageBox(data) {
    $("#message-box").find(".modal-body").html(data);
    $("#message-box").modal("show");
}