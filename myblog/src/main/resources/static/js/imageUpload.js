$(function () {
    initFileInput("input-images");
});
var showUrl = $("#input");

function initFileInput(ctrlName) {
    var control = $('#' + ctrlName);
    control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: "/uploadImages", //上传的地址
        allowedFileExtensions: ['jpg', 'png', 'gif', 'jpeg'],//接收的文件后缀
        //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove: true, //显示移除按钮
        showPreview: true, //是否显示预览
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        //dropZoneEnabled: true,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        //maxFileCount: 10, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount: true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        layoutTemplates: {
            //actionDelete:'', //去除上传预览的缩略图中的删除图标
            //actionUpload:'',//去除上传预览缩略图中的上传图片；
            //actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
        },
        uploadExtraData: function () {//扩展参数
            return {};
        }
    }).on('filepreupload', function (event, data, previewId, index) {     //上传中
    }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
        console.log('文件上传成功！');
        // debugger
        console.log('文件上传成功！' + data.response.id);
        console.log(data.response);
        // showUrl.append('<input id="url1" value="'+ data.response.data +'"><button id="copy" data-clipboard-target="#url1">复制input框中的内容</button>\n' );
        showUrl.html("![](" + data.response.data + ")");
        copyUrl();


    }).on('fileerror', function (event, data, msg) {  //一个文件上传失败
        console.log('文件上传失败！' + data.id);
    })
}

function copyUrl() {

}

var clipboard = new ClipboardJS('#copy');
// 显示用户反馈/捕获复制/剪切操作后选择的内容
clipboard.on('success', function (e) {
    console.info('Action:', e.action);//触发的动作/如：copy,cut等
    console.info('Text:', e.text);//触发的文本
    console.info('Trigger:', e.trigger);//触发的DOm元素
    e.clearSelection();//清除选中样式（蓝色）
})
clipboard.on('error', function (e) {
    console.error('Action:', e.action);
    console.error('Trigger:', e.trigger);
});

var imageUrl = $("#input-url");
var imageName = $("#input-name");
var buttonSubmit = $("#submit-url");
var inputUrl = $("#input-url-hand");
var copyUrl = $("#copy-url");
buttonSubmit.click(function () {
    downloadImage();
});

function downloadImage() {
    $.ajax({
        type: "POST",
        url: "/downImage",
        datatype: "json",
        data: {url: imageUrl.val(), imageName: imageName.val()},
        success: function (data) {
            console.log(data);
            showUrl.html("![](" + data.data + ")");
        }
    })
}