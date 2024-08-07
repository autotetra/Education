//var

function example1() {
    var x = 1;
    if (true) {
        var x = 2; // Same variable as the one declared above
        console.log(x); // 2
    }
    console.log(x); // 2
}


//let
function example2(){
    let y = 1;
    if(true){
        let y = 2; // Different variable from the one declared above
        console.log(y); // 2
    }
    console.log(y) // 1
}

//const
function example3(){
    const z = 1;
    if(true){
        const z = 2; // Different variable from the one declared above
        console.log(z); // 2
    }
    console.log(z); // 1
}

example1()
example2()
example3()