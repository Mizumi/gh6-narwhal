'use strict';

import route from './agent.route';

const agentPageModule = angular.module('agent-module', [
  'ui.router'
]);

agentPageModule
    .config(route);

export default agentPageModule;
