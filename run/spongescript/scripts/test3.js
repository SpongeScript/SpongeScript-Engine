function x() { var x; }

print(x);
print(Java.extend);


var output = '';
var object = Java;
for (var property in object) {
    output += property + ': ' + object[property]+'; ';
}
print(output);