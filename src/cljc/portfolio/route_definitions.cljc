(ns portfolio.route-definitions)

(def routes
  ["" {"/" :portfolio
       "/portfolio" {["/" :id] :portfolio-entry}
       "/notes" {"" :notes
                 ["/" :id] :notes-entry}
       "/public" {true :static}
       true :not-found}])
