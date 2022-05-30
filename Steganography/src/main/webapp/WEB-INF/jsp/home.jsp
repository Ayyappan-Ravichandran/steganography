<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>steganography</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    
    <section class="left-section">
        <div id="left-cover" class="cover cover-hide">
            <img src="img/blackcover.png" alt="">                       
            <button type="button" class="switch-btn" onclick="location.reload()">Encode</button>
        </div>
        <div id="left-form" class="form fade-in-element">
            <h1>Encode</h1>
            <form action="encode" method="post"  enctype="multipart/form-data">
                <input type="file" name="image" class="input-box" placeholder="Select File">
                <input type="text" name="message" class="input-box" placeholder="message">
                <input type="text" name="key" class="input-box" placeholder="key">
                <input type="submit" name="login-btn" class="btn" value="Encode">
                <h5><span class="message">${message}</span></h5>
                <h5><span class="message">${errorMessage}</span></h5>
            </form>
        </div>
    </section>

    <section class="right-section">
        <div id="right-cover" class="cover fade-in-element">
            <img src="img/blackcover.png" alt="">            
            <button type="button" class="switch-btn" onclick="switchSignup()">Decode</button>
        </div>
        <div id="right-form" class="form form-hide">
            <h1>Decode</h1>
            <form action="decode" method="post"  enctype="multipart/form-data">
                <input type="file" name="image" class="input-box" placeholder="Select File">
                <input type="text" name="key" class="input-box" placeholder="key">
                <input type="submit" name="signup-btn" class="btn" value="Decode">
                <h5><span class="message">${message}</span></h5>
                <h5><span class="message">${errorMessage}</span></h5>
            </form>
        </div>
    </section>

    <script src="js/main.js"></script>

</body>
</html>