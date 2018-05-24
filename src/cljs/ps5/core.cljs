(ns ps5.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [ps5.events :as events]
            [ps5.routes :as routes]
            [ps5.views :as views]
            [ps5.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
