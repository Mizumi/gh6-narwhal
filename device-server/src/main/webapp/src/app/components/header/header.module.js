'use strict';

import headerDirective from './header.directive';
import './header.scss';

const headerModule = angular.module('header-module', []);

headerModule
  .directive('gh6Header', headerDirective);

export default headerModule;
