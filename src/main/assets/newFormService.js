/* global $, Exception, console*/

/**
 * Handle new form creation
 *
 * @class NewFormService
 * @extends HUBU.AbstractComponent
 */

function NewFormService() {
    "use strict";

    var self = this;
    var _hub;
    var _model;
    var _url = "/list";

    self.name = "NewFormService";

    self.getComponentName = function() {
        return self.name;
    };

    self.newFormRender = null; //RactiveRenderService - TodoListRender

    /**
     * Configure the instance of the TodoListController.
     *
     * @method configure
     * @param {HUBU.hub} theHub
     * @param conf - The TodoListController configuration.
     * @param {map} conf.model - The model link to this TodoListController
     * @param {string} [conf.url='/list'] - The root URL of the todo lists
     */
    self.configure = function(theHub, conf) {
        _hub = theHub;

        if (typeof conf == "undefined") {
            throw new Exception("The TodoListController configuration is mandatory.");
        }

        if (typeof conf.model !== "object") {
            throw new Exception("The model entry is mandatory.");
        }

        //Check with a regexp
        if (typeof conf.url === "string") {
            _url = conf.url;
        }

        _model = conf.model;

        _hub.requireService({
            component: this,
            contract: window.RactiveRenderService,
            field: "newFormRender"
        });
    };

    function encodeIdURL(root,id){
      return root+"/"+id.replace("#","%23").replace(":","%3A");
    }


    /**
     * Add a new text entry to the form
     * @param event - The Ractive event
     */
    function addTextEntry(event){
        _model.entries.push(
            {
                label: 'New label',
                info: ''
            }
        );
    }

    self.start = function() {
        self.newFormRender.render();

        //bind the event
        self.newFormRender.on("addTextEntry", addTextEntry);
    };

    self.stop = function() {

    };
}
