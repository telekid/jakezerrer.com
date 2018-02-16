(ns portfolio.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [portfolio.core-test]
   [portfolio.common-test]))

(enable-console-print!)

(doo-tests 'portfolio.core-test
           'portfolio.common-test)
