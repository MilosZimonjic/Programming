let crashRide = document.querySelector('#crash-ride');
console.log(crashRide);
let hiHatTop = document.querySelector('#hihat-top');


const animateCrashOrRide = () => {
    crashRide.style.transform = 'rotate(0deg) scale(1.5)';
}

const animateHiHatClosed = () => {
    hiHatTop.style.top = '171px';
}

window.addEventListener('keydown', () => {
    //pokupim key code stisnutog dugmeta
    let code = event.keyCode;


    //nadjem u html element koji ima keycode isti kao stisnuto dugme
    let keyElement = document.querySelector(`div[data-key="${code}"]`) //znaci dobijemo div

    if (!keyElement) return;
    //prekinemo listener samo = i to je to



    let audio = document.querySelector(`audio[data-key= "${code}"]`);
    /*Probaj bez sledece linije koda da klikces dugme uzastopno da vidis sta se desava */
    audio.currentTime = 0;
    audio.play();

    switch (code) {
        case 69:
        case 82:
            animateCrashOrRide();
            break;
        case 75:
        case 73:
            animateHiHatClosed();
            break;
    }

});

const removeCrashRideTransition = e => {
    if (e.propertyName !== 'transform') return;
    e.target.style.transform = 'rotate(-7.2deg) scale(1.5)';
}

const removeHiHatTopTransition = e => {
    if (e.propertyName !== 'top') return;
    e.target.style.top = '166px';
}

crashRide.addEventListener("transitionend", removeCrashRideTransition);
hiHatTop.addEventListener("transitionend", removeHiHatTopTransition);