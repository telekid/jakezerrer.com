(ns portfolio.route-definitions)

(def routes [{:route-id :home
              :test-path "/"}
             {:route-id :portfolio
              :test-path "/portfolio"
              :routes [{:route-id :portfolio-entry
                        :test-path ["/" :id]}]}
             {:route-id :public
              :test-path "/public"
              :routes [{:route-id :public-asset
                        :test-path true}]}
             {:route-id :not-found
              :test-path true}])
