<%-- 
    Document   : ajax
    Created on : Mar 26, 2019, 11:32:41 AM
    Author     : mariana
--%>

<html>
    <head>
        <TITLE>Crunchify - Spring MVC Example with AJAX call</TITLE>

        <style type="text/css">
            body {
                background-image:
                    url('https://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
            }
        </style>
        <script type="text/javascript"
        src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script type="text/javascript">
            function crunchifyAjax() {
                $.ajax({
                    url: 'ajaxtest.htm',
                    success: function (data) {
                        $('#result').html(data);
                    }
                });
            }

            // function crunchifyAjaxList() {
            //     $.ajax({
            //         url: 'ajaxList.htm',
            //         success: function (data) {
            //             $('#resultList').html(data);
            //         }
            //     });
            // }

            function operonStatus() {
                $.ajax({
                    url: 'ajaxList.htm',
                    success: function (data) {
                        $('#resultList').html(data);
                        var geneList = data;
                        
                        var res = geneList.split(",");
                        alert(res[1]);

                    }
                });
            }

        </script>

        <script type="text/javascript">
            var intervalId = 0;
            intervalId = setInterval(crunchifyAjax, 3000);
            var intervalId2 = 0;
            intervalId2 = setInterval(operonStatus, 3000);
        </script>
    </head>

    <body>
        <div align="center">
            <br> <br> ${message} <br> <br>
            <div id="result"></div>
            jsjssjss
            <div id="resultList"></div>

            <br>
            <p>
                by <a href="https://crunchify.com">Crunchify.com</a>
            </p>
        </div>
    </body>
</html>
