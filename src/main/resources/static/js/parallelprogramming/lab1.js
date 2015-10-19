var interrupted = false;


$(function () {
    $("#start").on("click", function () {
        startSorting();
    });

    $("#pause").on("click", function () {
        pause();
    });

    $("#abort").on("click", function () {
        abort();
    });
});

function getSortStatus() {
    $.post("/pp/lab1/sorting/action?type=status", function (data) {
        if (interrupted) {
            return;
        }
        $("#insertionProgress").css('width', data.insertionSortStatus + '%').attr('aria-valuenow', data.insertionSortStatus);
        $("#insertionProgress").text(data.insertionSortStatus + '% Complete');
        $("#qSortProgress").css('width', data.quickSortStatus + '%').attr('aria-valuenow', data.quickSortStatus);
        $("#qSortProgress").text(data.quickSortStatus + '% Complete');
        $("#qSortDuration").text("duration: " + data.quickSortDuration + "ms");
        $("#insertionSortDuration").text("duration: " + data.insertionSortDuration + "ms");
        if (data.insertionSortStatus == 100) {
            $("#insertData").text("[" + data.insertionSortingDataBegin.join(", ") + "]");
        }
        if (data.quickSortStatus == 100) {
            $("#qsData").text("[" + data.quickSortingDataBegin.join(", ") + "]");
        }
        if (!data.complete && !interrupted) {
            setTimeout(getSortStatus, 300);
        } else {
            return;
        }
    });
}

function startSorting() {
    $("#pause").removeAttr("disabled");
    $("#abort").removeAttr("disabled");
    $.post("/pp/lab1/sorting/action?type=start", function (data) {
        getSortStatus();
    });
}

function pause() {
    $.post("/pp/lab1/sorting/action?type=pause", function (data) {
        if (data.paused) {
            $("#pause").text("resume");
            if (data.quickSortStatus != 100) {
                $("#qSortProgress").removeClass("progress-bar-success").addClass("progress-bar-warning");
            }
            if (data.insertionSortStatus != 100) {
                $("#insertionProgress").removeClass("progress-bar-success").addClass("progress-bar-warning");
            }
        } else {
            $("#pause").text("pause");
            $("#qSortProgress").removeClass("progress-bar-warning").addClass("progress-bar-success");
            $("#insertionProgress").removeClass("progress-bar-warning").addClass("progress-bar-success");
        }
    });
}

function abort() {
    interrupted = true;
    $.post("/pp/lab1/sorting/action?type=abort", function (data) {
        $("#pause").attr("disabled", "disabled");
        $("#abort").attr("disabled", "disabled");
        $("#start").attr("disabled", "disabled");
        $("#qSortProgress").removeClass("progress-bar-success").addClass("progress-bar-danger");
        $("#insertionProgress").removeClass("progress-bar-success").addClass("progress-bar-danger");
    });
}

function toggler(divId) {
    $("#" + divId).toggle();
}
