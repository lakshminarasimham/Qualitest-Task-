@test
  Feature: Add Products into wishlist and Cart
    Scenario: Add four product into the wishlist and add lowest price product into the cart
      Given I add four different products to my wishlist
      When I view my wishlist table
      Then I find my total four selected items in my wishlist
      When I search for lowest price product
      And I am able to add the lowest price item to my cart
      Then I am able to verify the item in my cart