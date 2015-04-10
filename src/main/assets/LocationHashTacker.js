/**
 * This H-Ubu component publish an event each time the location hash change. The event
 * contains the new hash.
 *
 * Default topic is `"/path/change"`
 * event : `{ "path" : <newhash> }`
 * @class LocationHashTracker
 */
function LocationHashTacker() {
    "use strict";

    var self = this;
    var _hub = null;
    var _topic  = "/path/change";
    var _interId = null;
    var _oldHash = null;

    /**
     * This component name.
     */
    self.name = "LocationHashTacker";

    /**
    * Callback call when location.hash has change.
    * It publish the new hash through the hub.
    * @Callback LocationHashTacker~trackChange
    */
    function trackChange(){
        var currentHash = location.hash;
        if (currentHash !== _oldHash){
            _hub.publish(self, _topic, {"path" : currentHash, "oldPath" : _oldHash});  //Publish the hash change.
            _oldHash = currentHash;
        }
    }

    /**
     * @method configure
     */
    self.configure = function(theHub, conf){
        _hub = theHub;

        if(conf === undefined){
            return; //no configuration
        }

        //set name if changed
        if(typeof conf.name === "string"){
            self.name = conf.name;
        }

        //If the property `topic` has been define, check if valid and use it.
        if( (typeof conf.topic === "string") &&  conf.topic.match(/^\/\w+(\/\w+)*$/) ){
            _topic = conf.topic;
        } else if (typeof conf.topic !== "undefined"){
            throw new Exception("The property topic must be a valid topic string.");
        }
    };

    /**
     * @method start
     */
    self.start = function(){
        //propagate the hash
        trackChange();

        //listen to hash change
        if ("onhashchange" in window && (!document.documentMode || document.documentMode >= 8)) {
            window.onhashchange = trackChange;
        } else {
            _interId = setInterval(trackChange, 50);
        }
    };

    /**
     * @method stop
     */
    self.stop = function(){
        if (_interId === null) {
            window.onhashchange = undefined;
        } else {
            clearInterval(_interId);
        }
    };

    /**
     * @method getTopic
     */
    self.getTopic = function(){
        return _topic;
    };

    /**
     * @method getComponentName
     */
    LocationHashTacker.prototype.getComponentName = function(){
        return this.name;
    };

    /**
     * @method toString
     * @memberof LocationHashTacker.prototype
     */
    LocationHashTacker.prototype.toString = function(){
        return "[LocationHashTracker][topic:"+this.getTopic()+"]" ;
    };
}
