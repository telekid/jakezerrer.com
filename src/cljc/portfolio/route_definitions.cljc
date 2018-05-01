(ns portfolio.route-definitions)

(def routes [{:route-id :home
              :pattern "/"}
             {:route-id :portfolio
              :pattern "/portfolio"
              :routes [{:route-id :portfolio-entry
                        :pattern ["/" :id]}]}
             {:route-id :public
              :pattern "/public"
              :routes [{:route-id :public-asset
                        :pattern true}]}
             {:route-id :not-found
              :pattern true}])
