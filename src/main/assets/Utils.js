/**
 * Swap two elements of the Array.
 *
 * @param from - The index of the first element to be swaped.
 * @param to - The index of the second element to be swaped.
 * @return true if the the array has been modified because of this method, false otherwise.
 */
Array.prototype.swap = function(from, to) {
    //check integer
    if(typeof from !== 'number' || typeof to !== 'number'){
        throw new Exception('The given parameter must be a number');
    }

    if(from === to || from < 0 || from > this.length || to < 0 || to > this.length){
        return false;
    }

    var temp = this[from];
    this[from]=this[to];
    this[to]=temp;
    temp=null;

    return true;
}