(ns portfolio.route-definitions)

(def routes
  ["" {"/" :home
       "/portfolio" {"" :portfolio
                     ["/" :id] :portfolio-entry}
       "/notes" {"" :notes
                 ["/" :id] :notes-entry}
       "/public" {true :static}
       true :not-found}])
